package com.kedacom.publish;

import com.kedacom.event.ProduceMessageEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

/**
 * @Auther: YinPeng
 * @Date: 2018/8/30 0030 15:16
 * @Description:
 */
@Component
@Slf4j
public class ProduceMessageEventPublisher implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher publisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }

    public void publish() {
        ProduceMessageEvent ce = new ProduceMessageEvent(this);
        log.info("正在发布事件...");
        publisher.publishEvent(ce);
        log.info("发布事件结束！");
    }
}
