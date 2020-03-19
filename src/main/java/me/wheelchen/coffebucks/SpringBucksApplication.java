package me.wheelchen.coffebucks;

import me.wheelchen.coffebucks.model.Coffee;
import me.wheelchen.coffebucks.model.CoffeeOrder;
import me.wheelchen.coffebucks.service.CoffeeOrderService;
import me.wheelchen.coffebucks.service.CoffeeService;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Kelvin Chen
 * @date 2020-03-18 23:18:20
 */
@SpringBootApplication
public class SpringBucksApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringBucksApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        System.out.println(coffeeService.findAllCoffee());
    }
}
