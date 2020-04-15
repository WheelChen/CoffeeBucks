package me.wheelchen.coffeebucks.waiter.common.config;

import me.wheelchen.coffeebucks.waiter.common.interceptor.PerformanceInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 定制化Web Spring MVC
 * @author Kelvin Chen
 * @date 2020-04-04 00:19:31
 */
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    /**
     * 为特定路径添加拦截器
     * @param registry 拦截器注册器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new PerformanceInterceptor())
                .addPathPatterns("/coffee/**")
                .addPathPatterns("/order/**");
    }
}
