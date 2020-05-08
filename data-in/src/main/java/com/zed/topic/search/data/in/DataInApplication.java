package com.zed.topic.search.data.in;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import tk.mybatis.spring.annotation.MapperScan;

import java.util.logging.Level;


@SpringBootApplication
@ComponentScan({"com.zed.topic.search.core","com.zed.topic.search.data.in"})
@EnableDiscoveryClient
@EntityScan("com.zed.topic.search.core.pojo")
@EnableJpaRepositories("com.zed.topic.search.core.repo")
public class DataInApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataInApplication.class, args);
    }

}
