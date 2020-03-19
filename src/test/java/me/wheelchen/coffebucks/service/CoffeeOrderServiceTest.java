package me.wheelchen.coffebucks.service;

import me.wheelchen.coffebucks.model.Coffee;
import me.wheelchen.coffebucks.model.CoffeeOrder;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;


/**
 * @author Kelvin Chen
 * @date 2020-03-19 11:39:38
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CoffeeOrderServiceTest {
    @Autowired
    private CoffeeOrderService coffeeOrderService;

    @Autowired
    private CoffeeService coffeeService;

    @Test
    public void createOrder() {
        Optional<Coffee> coffee = coffeeService.findOneByName("latte");

//        if (coffee.isPresent()) {
//            CoffeeOrder coffeeOrder = coffeeOrderService.createOrder("test", coffee.get());
//        }

        coffee.ifPresent((c -> {
            CoffeeOrder coffeeOrder = coffeeOrderService.createOrder("test", c);
            System.out.println(coffeeOrder);
        }));

    }
}