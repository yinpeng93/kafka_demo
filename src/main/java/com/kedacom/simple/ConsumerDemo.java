package com.kedacom.simple;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

/**
 * @Auther: YinPeng
 * @Date: 2018/8/29 0029 13:11
 * @Description:
 */
public class ConsumerDemo {
    public static void main(String[] args) {
        Properties properties = new Properties();
        //kafka的brokers代理集群
        properties.put("bootstrap.servers", "139.224.37.87:9092");
        //消费者分组id
        properties.put("group.id", "group-1");
        //是否自动提交
        properties.put("enable.auto.commit", "true");
        //自动提交间隔
        properties.put("auto.commit.interval.ms", "1000");
        //earliest当各分区下有已提交的offset时，从提交的offset开始消费；无提交的offset时，从头开始消费
        //latest 当各分区下有已提交的offset时，从提交的offset开始消费；无提交的offset时，消费新产生的该分区下的数据
        //none
        //topic各分区都存在已提交的offset时，从offset后开始消费；只要有一个分区不存在已提交的offset，则抛出异常
//        properties.put("auto.offset.reset", "earliest");
        properties.put("auto.offset.reset", "latest");
        //超时时间
        properties.put("session.timeout.ms", "15000");
        //每次拉取最大的消息数量
        properties.put("max.poll.records", 10);
        //序列化
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(properties);
        //订阅主题列表topic
        kafkaConsumer.subscribe(Arrays.asList("HelloWorld"));

        while (true) {

            ConsumerRecords<String, String> records = kafkaConsumer.poll(100);

//            System.out.println("1111");
            //手动提交offset
//            kafkaConsumer.commitSync();
            for (ConsumerRecord<String, String> record : records) {
//                try {
////                    Thread.sleep(25000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                System.out.printf("offset = %d, value = %s", record.offset(), record.value());
                System.out.println();
            }
        }

    }
}
