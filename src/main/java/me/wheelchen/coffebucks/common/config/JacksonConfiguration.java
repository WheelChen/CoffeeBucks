package me.wheelchen.coffebucks.common.config;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.TimeZone;

/**
 * 配置Jackson
 * @author Kelvin Chen
 * @date 2020-04-04 00:34:33
 */
@Configuration
public class JacksonConfiguration {

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return builder -> {
            // 格式化输出
            builder.indentOutput(true);
            // 设置时区
            builder.timeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        };
    }

    @Bean
    public Hibernate5Module hibernate5Module() {
        return new Hibernate5Module();
    }
}
