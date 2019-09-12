package com.lwq.www;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author: LWQ
 * @create: 2019/9/5
 * @description: SimpleKafkaConsumer
 **/
public class SimpleKafkaConsumer {
    private static String bootstrapServers = "192.168.233.130:9092";
    private static String topic = "simple_topic";
    private static String groupId = "simple_group";
    private static AtomicBoolean isRunning = new AtomicBoolean(true);

    public static void main(String[] args) {
        Properties properties = initConfig();
        KafkaConsumer kafkaConsumer = new KafkaConsumer(properties);
        kafkaConsumer.subscribe(Arrays.asList(topic));
        while (isRunning.get()) {
            ConsumerRecords consumerRecords = kafkaConsumer.poll(Duration.ofSeconds(5));
            Iterator<ConsumerRecord> iterator = consumerRecords.iterator();
            while (iterator.hasNext()) {
                ConsumerRecord consumerRecord = iterator.next();
                System.out.println("topic:" + consumerRecord.topic() + ",partition:" + consumerRecord.partition() +
                        ",offset:" + consumerRecord.offset() + ",value:" + consumerRecord.value());
            }
            kafkaConsumer.commitSync();
        }
        kafkaConsumer.close();
    }

    private static Properties initConfig() {
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        return properties;
    }
}
