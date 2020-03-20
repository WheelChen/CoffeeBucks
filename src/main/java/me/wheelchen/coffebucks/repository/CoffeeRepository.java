package me.wheelchen.coffebucks.repository;

import me.wheelchen.coffebucks.model.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * 咖啡操作
 * @author Kelvin Chen
 * @date 2020-03-19 10:59:14
 */
public interface CoffeeRepository extends JpaRepository<Coffee, Long> {
    /**
     * 按咖啡名查找
     *
     * @param name 咖啡名
     * @return 咖啡
     */
    Optional<Coffee> findOneByName(String name);
}
