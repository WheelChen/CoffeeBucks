package me.wheelchen.coffebucks.config;

import io.lettuce.core.ReadFrom;
import me.wheelchen.coffebucks.converter.BytesToMoneyConverter;
import me.wheelchen.coffebucks.converter.MoneyToBytesConverter;
import org.springframework.boot.autoconfigure.data.redis.LettuceClientConfigurationBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.convert.RedisCustomConversions;

import java.util.Arrays;

/**
 * @author Kelvin Chen
 * @date 2020-03-19 18:28:41
 */
@Configuration
public class RedisCustomizeConfig {

    /**
     * 设置 从主服务器读数据
     * @return 自定义LettuceClientConfigurationBuilder
     */
    @Bean
    public LettuceClientConfigurationBuilderCustomizer customizer() {
        return builder -> builder.readFrom(ReadFrom.MASTER_PREFERRED);
    }

    /**
     * 注入自定义Redis Converter
     *
     * @return 返回添加自定义converter的Conversions
     */
    @Bean
    public RedisCustomConversions redisCustomConversions() {
        return new RedisCustomConversions(
                Arrays.asList(new MoneyToBytesConverter(), new BytesToMoneyConverter()));
    }

}
