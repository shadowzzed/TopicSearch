package com.zed.topic.search.data.in.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author Zed
 * @date 2020/4/29 下午4:42
 * @contact shadowl91@163.com
 */
@Configuration
@RefreshScope
@Data
public class DataInConfiguration {

    @Value("${user.name}")
    private String username;

    @Value("${config.host.nlpAdd}")
    public String nlpAdd;

    @Value("${config.host.nlpPort}")
    public String nlpPort;

    @Value("${config.host.wormAdd}")
    public String wormAdd;

    @Value("${config.host.wormPort}")
    public String wormPort;
}
