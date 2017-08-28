package com.fan.demo;

import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;

import com.fan.demo.dto.AbstractMessage;
import com.fan.demo.factory.KafkaFactory;
import com.fan.demo.util.MessageCounter;
import com.fan.demo.util.PropertiesUtils;
import com.fan.demo.util.SerializeUtil;

public class KafkaConsumerTest {

    Consumer<String, byte[]> consumer;

    boolean isAutoCommitOffset = false;

    void rec() throws Exception {

        Properties props = PropertiesUtils.getProperties("config.properties");
        long monitorCycle = PropertiesUtils.getLong(props, "monitor.cycle", 10000);
        String topic = PropertiesUtils.getString(props, "test.topic", "test");
        String messagePrint = PropertiesUtils.getString(props, "rec.message.print", "y");

        consumer = KafkaFactory.<String, byte[]> createConsumer();
        consumer.subscribe(Collections.singletonList(topic));

        MessageCounter messageCounter = new MessageCounter("接收", monitorCycle);

        long count = 0;
        while (true) {
            ConsumerRecords<String, byte[]> records = consumer.poll(1);

            if (!records.isEmpty()) {
                for (ConsumerRecord<String, byte[]> record : records) {

                    AbstractMessage message = null;
                    try {
                        messageCounter.setNumber(1);
                        messageCounter.setCount(record.value().length);
                        message = (AbstractMessage) SerializeUtil.unserialize(record.value());
                        
                        if ("y".equals(messagePrint)) {
                            System.out.println("收到消息:" + message.getSeq());
                        }

                        if (count == 0) {
                            count = message.getSeq();
                        } else {
                            if (count + 1 != message.getSeq()) {
                                System.out.println("丢失消息:" + (count + 1));
                                count = 0;
                            } else {
                                count = message.getSeq();
                            }
                        }

                    } catch (Exception ex) {
                        // 防止出现反序列化失败，导致消息阻塞的问题。消息记录日志后直接抛弃。
                        System.out.println("消息反序列化失败");
                        ex.printStackTrace();
                    }

                }

                commitKafkaOffset();

            }

        }

    }

    /**
     * 手动更新kafka-offset只针对MessageType.File
     */
    public void commitKafkaOffset() {
        if (!isAutoCommitOffset && null != consumer) {
            consumer.commitAsync();
        }
    }

    public static void main(String[] args) throws Exception {
        new KafkaConsumerTest().rec();
    }

}
