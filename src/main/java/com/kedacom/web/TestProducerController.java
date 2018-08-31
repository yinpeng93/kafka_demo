package com.kedacom.web;

import com.kedacom.producer.KafkaNativeProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: YinPeng
 * @Date: 2018/8/30 0030 16:59
 * @Description:
 */
@RestController
public class TestProducerController {
    @Autowired
    KafkaNativeProducer kafkaNativeProducer;

    @RequestMapping("/produce")
    public String producer(){
        kafkaNativeProducer.sendMsg("producer 1");
        return "true";
    }
}
