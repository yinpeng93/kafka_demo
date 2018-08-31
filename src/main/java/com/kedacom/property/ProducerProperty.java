package com.kedacom.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Auther: YinPeng
 * @Date: 2018/8/30 0030 14:12
 * @Description:
 */

@Data
@Component
@ConfigurationProperties(prefix = "kafka.producer")
public class ProducerProperty {
    /**
     * 集群
     */
    private String servers;

    /**
     * 重试次数
     */
    private Integer retries;

    private String acks;

    private Integer batchSize;

    private Integer linger;

    private Integer bufferMemory;

    private String keySerializer;

    private String valueSerializer;
}
