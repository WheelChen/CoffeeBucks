package me.wheelchen.coffebucks.controller;

import me.wheelchen.coffebucks.model.Coffee;
import me.wheelchen.coffebucks.service.CoffeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Kelvin Chen
 * @date 2020-03-21 22:56:11
 */
@RestController
@RequestMapping("/coffee")
public class CoffeeController {
    private final CoffeeService coffeeService;

    public CoffeeController(CoffeeService coffeeService) {
        this.coffeeService = coffeeService;
    }

    @GetMapping("/")
    public List<Coffee> getAll() {
        return coffeeService.findAllCoffee();
    }

}
