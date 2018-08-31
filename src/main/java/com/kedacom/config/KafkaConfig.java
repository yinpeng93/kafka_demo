package com.kedacom.config;

import com.kedacom.producer.KafkaNativeProducer;
import com.kedacom.property.ConsumerProperty;
import com.kedacom.property.ProducerProperty;
import com.kedacom.publish.ProduceMessageEventPublisher;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @Auther: YinPeng
 * @Date: 2018/8/30 0030 14:35
 * @Description:
 */
@Configuration
public class KafkaConfig {

    @Autowired
    private ProducerProperty producerProperty;

    @Autowired
    private ConsumerProperty consumerProperty;

    @Autowired
    private ProduceMessageEventPublisher publisher;

    @Value("${kafka.group-id}")
    private String groupId;

    @Value("${kafka.topic}")
    private String topic;

    @Bean(name = "kafkaConsumerProperties")
    public Properties kafkaConsumerProperties() {

        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, consumerProperty.getServers());
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, consumerProperty.getEnableAutoCommit());
//        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, consumerProperty.getAutoCommitInterval());
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, consumerProperty.getSessionTimeout());
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, consumerProperty.getAutoOffsetReset());

        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);


        return props;
    }

    @Bean(name = "kafkaProducerProperties")
    public Properties kafkaProducerProperties() {

        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, producerProperty.getServers());
        props.put(ProducerConfig.MAX_BLOCK_MS_CONFIG, "10000");
//        props.put(ProducerConfig.ACKS_CONFIG,producerProperty.getAcks());
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.RETRIES_CONFIG, producerProperty.getRetries());
        props.put(ProducerConfig.LINGER_MS_CONFIG, producerProperty.getLinger());
//        props.put(ProducerConfig.BATCH_SIZE_CONFIG,producerProperty.getBatchSize());
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG,producerProperty.getBufferMemory());
        return props;
    }

    @Bean(name = "kafkaNativeProducer")
    public KafkaNativeProducer kafkaNativeProducer(Properties kafkaProducerProperties) {
        return new KafkaNativeProducer(kafkaProducerProperties,topic,publisher);
    }
}
