package com.zed.topic.search.nlp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class NlpApplication {

    public static void main(String[] args) {
        SpringApplication.run(NlpApplication.class, args);
    }

}
