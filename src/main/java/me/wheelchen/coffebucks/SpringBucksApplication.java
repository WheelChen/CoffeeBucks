package me.wheelchen.coffebucks;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

/**
 * @author Kelvin Chen
 * @date 2020-03-18 23:18:20
 */
@Slf4j
@SpringBootApplication
@EnableJpaRepositories
@EnableRedisRepositories
@EnableCaching
public class SpringBucksApplication {

    /**
     * Spring启动方法
     * @param args 接收参数
     */
    public static void main(String[] args) {
        SpringApplication.run(SpringBucksApplication.class, args);
    }



}
