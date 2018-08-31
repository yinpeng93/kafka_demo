package com.kedacom.producer;

import com.kedacom.publish.ProduceMessageEventPublisher;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

@Slf4j
public class KafkaNativeProducer {
    private KafkaProducer<String, String> producer;
    private String topic;
    private ProduceMessageEventPublisher publisher;

    public KafkaNativeProducer(Properties kafkaProperties, String topic, ProduceMessageEventPublisher publisher) {
        producer = new KafkaProducer<>(kafkaProperties);
        this.topic = topic;
        this.publisher = publisher;
    }

    public void sendMsg(String message) {
        log.info("正在向kafka的topic:{}发送message:{}",topic,message);

        try {
            producer.send(new ProducerRecord<>(topic, message));
        } catch (Exception e) {
            e.printStackTrace();
        }
        publisher.publish();
        log.info("发送成功！");
    }

}
