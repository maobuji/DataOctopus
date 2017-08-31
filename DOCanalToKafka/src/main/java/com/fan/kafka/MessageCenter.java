package com.fan.kafka;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.List;

/**
 * Created by fanmingming on 2017/8/31.
 */
public class MessageCenter {


    public static void sendMessage(List<String> lsJsonDate) throws Exception {

        Producer<String, String> producer = kafkaFactory.createProducer();
        String topic = "mysqlTopic";
        long time = System.currentTimeMillis();
        int i = 1;
        for (String json : lsJsonDate) {
            producer.send(new ProducerRecord<String, String>(topic, String.valueOf(time) + "_" + String.valueOf(i++), json));
        }

    }
}
