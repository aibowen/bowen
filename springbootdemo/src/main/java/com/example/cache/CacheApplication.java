package com.example.cache;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @description:
 * @author: bowen.wei@hand-china.com
 * @version: 1.0
 * @date: 2019-08-20 10:20
 */
@EnableCaching
@EnableScheduling
@SpringBootApplication
public class CacheApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder().sources(CacheApplication.class)
                .profiles("app").run(args);
    }
}
