package com.kedacom;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p></p>
 *
 * @author zhengfei
 * @create 2018-02-07 上午9:42
 **/
@SpringBootApplication(scanBasePackages = "com.kedacom")
@Slf4j
public class Bootstrap {

    public static void main(String[] args) {
        SpringApplication.run(Bootstrap.class, args);
        log.info("service started successfully");
    }

}
