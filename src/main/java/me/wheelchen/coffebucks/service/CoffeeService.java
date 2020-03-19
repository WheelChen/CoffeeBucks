package me.wheelchen.coffebucks.service;

import lombok.extern.slf4j.Slf4j;
import me.wheelchen.coffebucks.model.Coffee;
import me.wheelchen.coffebucks.repository.CoffeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * 咖啡表操作
 * @author Kelvin Chen
 * @date 2020-03-19 11:09:57
 */
@Slf4j
@Service
public class CoffeeService {

    private final CoffeeRepository coffeeRepository;

    public CoffeeService(CoffeeRepository coffeeRepository) {
        this.coffeeRepository = coffeeRepository;
    }


    public List<Coffee> findAllCoffee() {
        return coffeeRepository.findAll();
    }

    public Optional<Coffee> findOneByName(String name) {
        return coffeeRepository.findOneByName(name);
    }


}
