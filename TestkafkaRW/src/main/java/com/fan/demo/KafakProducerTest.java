package com.fan.demo;

import java.util.Properties;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import com.fan.demo.dto.AbstractMessage;
import com.fan.demo.dto.ByteMessage;
import com.fan.demo.dto.StringMessage;
import com.fan.demo.factory.KafkaFactory;
import com.fan.demo.util.MessageCounter;
import com.fan.demo.util.PropertiesUtils;
import com.fan.demo.util.SerializeUtil;

public class KafakProducerTest {

    void send() throws Exception {

        // 参数配置
        Properties props = PropertiesUtils.getProperties("config.properties");
        long monitorCycle = PropertiesUtils.getLong(props, "monitor.cycle", 10000);
        String topic = PropertiesUtils.getString(props, "test.topic", "test");
        long sendInterval = PropertiesUtils.getLong(props, "send.interval", 1000);
        int sendCount = PropertiesUtils.getInt(props, "send.count", 100);
        String sendType = PropertiesUtils.getString(props, "send.type", "msg");
        int sendBlockSize = PropertiesUtils.getInt(props, "send.block.size", 1024);
        String sendAck = PropertiesUtils.getString(props, " send.ack", "y");

        String messagePrint = PropertiesUtils.getString(props, "send.message.print", "y");

        // 计数器
        MessageCounter MessageCounter = new MessageCounter("发送", monitorCycle);

        // 创建发送者
        Producer<String, byte[]> producer = KafkaFactory.<String, byte[]> createProducer();

        for (int i = 0; i < sendCount; i++) {
            AbstractMessage message = null;
            if (sendType.equals("msg")) {
                message = new StringMessage();
            } else {
                message = new ByteMessage(sendBlockSize);
            }

            message.setSeq(i);
            try {

                byte[] bytes = SerializeUtil.serialize(message);

                if ("y".equals(sendAck)) {
                    producer.send(new ProducerRecord<String, byte[]>(topic, String.valueOf(i), bytes)).get();
                } else {
                    producer.send(new ProducerRecord<String, byte[]>(topic, String.valueOf(i), bytes));
                }

                if ("y".equals(messagePrint)) {
                    System.out.println("发送消息成功：" + i);
                }

                if (sendInterval > 0) {
                    Thread.sleep(sendInterval);
                }
                MessageCounter.setNumber(1);
                MessageCounter.setCount(bytes.length);
            } catch (Exception e) {
                System.out.println("发送消息失败：" + i);
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) throws Exception {
        new KafakProducerTest().send();
    }

}
