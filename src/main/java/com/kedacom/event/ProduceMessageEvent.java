package com.kedacom.event;

import org.springframework.context.ApplicationEvent;

/**
 * @Auther: YinPeng
 * @Date: 2018/8/30 0030 14:58
 * @Description:
 */
public class ProduceMessageEvent extends ApplicationEvent {
    public ProduceMessageEvent(Object source) {
        super(source);
    }
}
