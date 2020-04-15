package me.wheelchen.coffebucks.waiter.service;


import lombok.extern.slf4j.Slf4j;
import me.wheelchen.coffeebucks.waiter.WaiterServiceApplication;
import me.wheelchen.coffeebucks.waiter.model.Coffee;
import me.wheelchen.coffeebucks.waiter.model.CoffeeOrder;
import me.wheelchen.coffeebucks.waiter.model.OrderState;
import me.wheelchen.coffeebucks.waiter.service.CoffeeOrderService;
import me.wheelchen.coffeebucks.waiter.service.CoffeeService;
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
@SpringBootTest(classes = WaiterServiceApplication.class)
@Slf4j
@RunWith(SpringRunner.class)
public class CoffeeOrderServiceTest {
    @Autowired
    private CoffeeOrderService coffeeOrderService;

    @Autowired
    private CoffeeService coffeeService;

    /**
     * 测试创建订单
     */
    @Test
    public void createOrder() {
        Optional<Coffee> coffee = coffeeService.findOneByName("latte");

//        if (coffee.isPresent()) {
//            CoffeeOrder coffeeOrder = coffeeOrderService.createOrder("test", coffee.get());
//        }

        coffee.ifPresent(c -> {
            CoffeeOrder coffeeOrder = coffeeOrderService.createOrder("test", c);
            assertNotNull(coffeeOrder);
        });

    }

    /**
     * 测试更新订单状态
     */
    @Test
    public void updateState() {
        Optional<Coffee> latte = coffeeService.findOneCoffee("Latte");
        if (latte.isPresent()) {
            CoffeeOrder order = coffeeOrderService.createOrder("Li Lei", latte.get());
            log.info("Update INIT to PAID");
            assertTrue(coffeeOrderService.updateState(order, OrderState.PAID));

            log.info("Update PAID to INIT");
            assertFalse(coffeeOrderService.updateState(order, OrderState.INIT));

        }
    }
}