package me.wheelchen.coffebucks.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;


/**
 * @author Kelvin Chen
 * @date 2020-03-19 15:05:01
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CoffeeServiceTest {
    @Autowired
    private CoffeeService coffeeService;

    @Test
    public void findAllCoffee() {
        assertNotNull(coffeeService.findAllCoffee());
    }
}