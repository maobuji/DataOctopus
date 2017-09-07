package com.fan.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;

import java.util.Properties;

/**
 * Created by fanmingming on 2017/8/31.
 */
public class kafkaFactory {

    public static Producer<String, String> producer;

    public static Producer<String, String> createProducer() throws Exception {
        if (producer == null) {
            Properties props = PropertiesUtils.getProperties("E:\\DEV_Project\\DataOctopus\\DOCanalToKafka\\send.properties");
            producer = new KafkaProducer<String, String>(props);
        }
        return producer;
    }
}
