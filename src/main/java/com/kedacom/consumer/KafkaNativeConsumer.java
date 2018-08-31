package com.kedacom.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Properties;

/**
 * @Auther: YinPeng
 * @Date: 2018/8/30 0030 15:04
 * @Description:
 */
@Slf4j
@Component
public class KafkaNativeConsumer implements InitializingBean {

    @Autowired
    @Qualifier(value = "kafkaConsumerProperties")
    private Properties kafkaConsumerProperties;

    @Value("${kafka.topic}")
    private String topic;

    private KafkaConsumer consumer;

    /**
     * 从kafka获取消息
     */
    public void getMsgFromKafka() {
        log.info("consumer正在消费topic：{}中的消息",topic);
        ConsumerRecords<Integer, String> records = consumer.poll(300);
        if (records.isEmpty()) {
            return;
        }
        for (ConsumerRecord<Integer, String> record : records) {
            consumer(record);
        }
        consumer.commitSync();
    }

    private void consumer(ConsumerRecord<Integer, String> record) {
        log.info("get msg from kafka, partition:" + record.partition() + ",offset:" + record.offset() + ",value:" + record.value());
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        consumer = new KafkaConsumer(kafkaConsumerProperties);
        consumer.subscribe(Arrays.asList(topic));
    }
}
