package me.wheelchen.coffebucks.repository;

import me.wheelchen.coffebucks.model.CoffeeOrder;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 咖啡订单操作
 *
 * @author Kelvin Chen
 * @date 2020-03-19 10:59:20
 */
public interface CoffeeOrderRepository extends JpaRepository<CoffeeOrder, Long> {
}
