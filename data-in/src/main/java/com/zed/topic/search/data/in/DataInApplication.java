package com.zed.topic.search.data.in;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@ComponentScan({"com.zed.topic.search.core","com.zed.topic.search.data.in"})
@EnableDiscoveryClient
@MapperScan(basePackages = "com.zed.topic.search.core.mapper")
public class DataInApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataInApplication.class, args);
    }

}
