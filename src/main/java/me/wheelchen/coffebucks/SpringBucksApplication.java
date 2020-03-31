package me.wheelchen.coffebucks;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Kelvin Chen
 * @date 2020-03-18 23:18:20
 */
@Slf4j
@EnableTransactionManagement
@SpringBootApplication
@EnableJpaRepositories
@EnableRedisRepositories
@EnableCaching
//@EnableAspectJAutoProxy
public class SpringBucksApplication implements ApplicationRunner {

    /**
     * Spring启动方法
     * @param args 接收参数
     */
    public static void main(String[] args) {
        SpringApplication.run(SpringBucksApplication.class, args);
    }

    @Bean
    public Hibernate5Module hibernate5Module() {
        return new Hibernate5Module();
    }

    @Override
    public void run(ApplicationArguments args) {
//        Optional<Coffee> c = coffeeService.findSimpleCoffeeFromCache("mocha");
//        log.info("Coffee {}", c);
//
//        for (int i = 0; i < 5; i++) {
//            c = coffeeService.findSimpleCoffeeFromCache("mocha");
//        }
//
//        log.info("Value from Redis: {}", c);
    }
}
