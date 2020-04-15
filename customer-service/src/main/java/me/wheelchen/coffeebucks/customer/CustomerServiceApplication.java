package me.wheelchen.coffeebucks.customer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author Kelvin Chen
 * @date 2020-04-09 21:27:07
 */
@Slf4j
@SpringBootApplication
public class CustomerServiceApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(CustomerServiceApplication.class)
                .bannerMode(Banner.Mode.OFF)
                .web(WebApplicationType.NONE)
                .run(args);
    }
}
