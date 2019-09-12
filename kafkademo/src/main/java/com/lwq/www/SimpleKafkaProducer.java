package com.lwq.www;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * @author: LWQ
 * @create: 2019/9/4
 * @description: SimpleKafkaProducer
 **/
public class SimpleKafkaProducer {
    private static String bootstrapServers = "192.168.233.130:9092";
    private static String topic = "simple_topic";

    public static void main(String[] args) {
        Properties properties = initConfig();
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);
        for (int i = 0; i < 10; i++) {
            ProducerRecord<String, String> record = new ProducerRecord<>(topic, "number" + i);
            producer.send(record, new Callback() {
                @Override
                public void onCompletion(RecordMetadata metadata, Exception exception) {
                    if (exception == null) {
                        System.out.println("topic:" + metadata.topic() + ",partition:" + metadata.partition() + "," +
                                "offset:" + metadata.offset());
                    }
                }
            });
        }
        producer.close();
    }


    private static Properties initConfig() {
        Properties properties = new Properties();
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        return properties;
    }
}
