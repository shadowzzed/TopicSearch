package com.zed.topic.search.data.in;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.zed.topic.search.core")
public class DataInApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataInApplication.class, args);
    }

}
