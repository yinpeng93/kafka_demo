package com.kedacom.listener;

import com.kedacom.consumer.KafkaNativeConsumer;
import com.kedacom.event.ProduceMessageEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @Auther: YinPeng
 * @Date: 2018/8/30 0030 15:02
 * @Description:
 */
@Component
@Slf4j
public class ProduceMessageEventListener implements ApplicationListener<ProduceMessageEvent> {

    @Autowired
    KafkaNativeConsumer consumer;

    @Override
    public void onApplicationEvent(ProduceMessageEvent produceMessageEvent) {
        log.info("监听到事件");
        //监听到消息生产者在发送消息到kafka
        try {
            consumer.getMsgFromKafka();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
