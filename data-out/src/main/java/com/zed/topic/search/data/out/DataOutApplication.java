package com.zed.topic.search.data.out;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"com.zed.topic.search.core","com.zed.topic.search.data.out"})
@EntityScan("com.zed.topic.search.core.pojo")
@EnableJpaRepositories("com.zed.topic.search.core.repo")
public class DataOutApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataOutApplication.class, args);
    }

}
