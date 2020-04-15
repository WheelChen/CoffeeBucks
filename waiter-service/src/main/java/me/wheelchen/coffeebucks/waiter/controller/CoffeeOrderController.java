package me.wheelchen.coffeebucks.waiter.controller;

import lombok.extern.slf4j.Slf4j;
import me.wheelchen.coffeebucks.waiter.controller.request.NewOrderRequest;
import me.wheelchen.coffeebucks.waiter.model.Coffee;
import me.wheelchen.coffeebucks.waiter.model.CoffeeOrder;
import me.wheelchen.coffeebucks.waiter.service.CoffeeOrderService;
import me.wheelchen.coffeebucks.waiter.service.CoffeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@Slf4j
public class CoffeeOrderController {
    private final CoffeeOrderService orderService;
    private final CoffeeService coffeeService;

    public CoffeeOrderController(CoffeeOrderService orderService, CoffeeService coffeeService) {
        this.orderService = orderService;
        this.coffeeService = coffeeService;
    }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE,
                 produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public CoffeeOrder create(@RequestBody NewOrderRequest newOrder) {
        log.info("Receive new Order {}", newOrder);
        Coffee[] coffeeList = coffeeService.getCoffeeByName(newOrder.getItems())
                .toArray(new Coffee[]{});
        return orderService.createOrder(newOrder.getCustomer(), coffeeList);
    }

    @GetMapping("/{id}")
    public CoffeeOrder getOrder(@PathVariable("id") Long id) {
        return orderService.get(id);
    }
}
