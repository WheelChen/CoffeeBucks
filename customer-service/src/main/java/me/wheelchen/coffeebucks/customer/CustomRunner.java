package me.wheelchen.coffeebucks.customer;

import lombok.extern.slf4j.Slf4j;
import me.wheelchen.coffeebucks.customer.model.Coffee;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

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
        // Request - 1
        String baseUri = "http://localhost:8080/";
        URI uri = UriComponentsBuilder
                .fromUriString(baseUri + "coffee/?name={name}")
                .build("mocha");
        RequestEntity<Void> req = RequestEntity.get(uri)
                //设置返回接受类型
                .accept(MediaType.APPLICATION_XML)
                .build();
        ResponseEntity<String> resp = restTemplate.exchange(req, String.class);
        log.info("Response Status: {}, Response Headers: {}", resp.getStatusCode(), resp.getHeaders().toString());
        log.info("Coffee: {}", resp.getBody());

        // Request - 2
        String coffeeUri = baseUri + "coffee/";
        Coffee request = Coffee.builder()
                .name("Americano")
                .price(Money.of(CurrencyUnit.of("CNY"), 25.00))
                .build();
        Coffee response = restTemplate.postForObject(coffeeUri, request, Coffee.class);
        log.info("New Coffee: {}", response);

        // Request - 3 对于泛型的处理
        ParameterizedTypeReference<List<Coffee>> ptr =
                new ParameterizedTypeReference<List<Coffee>>() {
        };

        ResponseEntity<List<Coffee>> list = restTemplate
                .exchange(coffeeUri, HttpMethod.GET, null, ptr);
        list.getBody().forEach(c -> log.info("Coffee: {}", c));

    }
}
