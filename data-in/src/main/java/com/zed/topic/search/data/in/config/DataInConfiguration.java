package com.zed.topic.search.data.in.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author Zed
 * @date 2020/4/29 下午4:42
 * @contact shadowl91@163.com
 */
@Configuration
public class DataInConfiguration {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
