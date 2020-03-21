package me.wheelchen.coffebucks.service;

import lombok.extern.slf4j.Slf4j;
import me.wheelchen.coffebucks.model.Coffee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.Assert.*;


/**
 * @author Kelvin Chen
 * @date 2020-03-19 15:05:01
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class CoffeeServiceTest {
    @Autowired
    private CoffeeService coffeeService;

    /**
     * 测试查找所有咖啡
     */
    @Test
    public void findAllCoffee() {
        assertNotNull(coffeeService.findAllCoffee());
        String coffees = coffeeService.findAllCoffee().stream()
                .map(Coffee::toString)
                .collect(Collectors.joining("\n"));

        log.info(coffees);
    }

    /**
     * 测试是否从缓存中读取咖啡信息
     */
    @Test
    public void findSimpleCoffeeFromCache() {
        Optional<Coffee> c = coffeeService.findSimpleCoffeeFromCache("mocha");
        log.info("Coffee {}", c);


        for (int i = 0; i < 5; i++) {
            c = coffeeService.findSimpleCoffeeFromCache("mocha");
        }

        log.info("Value from Redis: {}", c);
    }
}