package me.wheelchen.coffeebucks.customer;

import lombok.extern.slf4j.Slf4j;
import me.wheelchen.coffeebucks.customer.model.Coffee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;

/**
 * @author Kelvin Chen
 * @date 2020-04-16 21:03:15
 */
@Slf4j
@Component
public class CustomRunner implements ApplicationRunner {
    private final RestTemplate restTemplate;

    public CustomRunner(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        // === GET 请求 & 序列化===
        // 构造URI
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:8080/coffee/{id}")
                .build(1);
        // 发送请求
        ResponseEntity<Coffee> c = restTemplate.getForEntity(uri, Coffee.class);
        log.info("Response Status: {}, Response Headers: {}", c.getStatusCode(), c.getHeaders().toString());
        log.info("Coffee: {}", c.getBody());

        // === POST 请求 & 序列化 ===
        String coffeeUri = "http://localhost:8080/coffee/";
        Coffee request = Coffee.builder()
                .name("Americano")
                .price(BigDecimal.valueOf(25.00))
                .build();
        Coffee response = restTemplate.postForObject(coffeeUri, request, Coffee.class);
        log.info("New Coffee: {}", response);

        // === GET 请求 ===
        String s = restTemplate.getForObject(coffeeUri, String.class);
        log.info("String: {}", s);
    }
}
