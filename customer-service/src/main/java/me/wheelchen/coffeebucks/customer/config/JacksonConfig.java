package me.wheelchen.coffeebucks.customer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.hal.Jackson2HalModule;

/**
 * jackson 注册HAL支持
 * @author Kelvin Chen
 * @date 2020-04-23 22:45:55
 */
@Configuration
public class JacksonConfig {
    @Bean
    public Jackson2HalModule jackson2HalModule() {
        return new Jackson2HalModule();
    }
}
