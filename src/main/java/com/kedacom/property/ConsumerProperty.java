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
@ConfigurationProperties(prefix = "kafka.consumer")
public class ConsumerProperty {
    /**
     * 集群
     */
    private String servers;

    private Boolean enableAutoCommit;

    private Integer sessionTimeout;

    private Integer autoCommitInterval;

    private String autoOffsetReset;

    private String keySerializer;

    private String valueSerializer;
}
