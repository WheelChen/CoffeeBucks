package me.wheelchen.coffeebucks.waiter.common.support;

import me.wheelchen.coffeebucks.waiter.service.CoffeeService;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

/**
 * 添加coffee数量的health indicator
 *
 * @author Kelvin Chen
 * @date 2020-05-06 23:22:47
 */
@Component
public class CoffeeIndicator implements HealthIndicator {
    private final CoffeeService coffeeService;

    public CoffeeIndicator(CoffeeService coffeeService) {
        this.coffeeService = coffeeService;
    }

    @Override
    public Health health() {
        long count = coffeeService.getCoffeeCount();
        Health health;
        if (count > 0) {
            health = Health.up()
                    .withDetail("count", count)
                    .withDetail("message", "We have enough coffee")
                    .build();
        } else {
            health = Health.down()
                    .withDetail("count", 0)
                    .withDetail("message", "We are out of coffee")
                    .build();
        }
        return health;
    }
}
