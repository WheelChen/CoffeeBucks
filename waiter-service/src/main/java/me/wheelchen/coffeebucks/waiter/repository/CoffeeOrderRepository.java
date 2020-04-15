package me.wheelchen.coffeebucks.waiter.repository;

import me.wheelchen.coffeebucks.waiter.model.CoffeeOrder;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 咖啡订单操作
 *
 * @author Kelvin Chen
 * @date 2020-03-19 10:59:20
 */
public interface CoffeeOrderRepository extends JpaRepository<CoffeeOrder, Long> {
}
