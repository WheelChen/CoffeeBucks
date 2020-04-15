package me.wheelchen.coffeebucks.waiter.repository;

import me.wheelchen.coffeebucks.waiter.model.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
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

    /**
     * 根据咖啡名查找、按id排序
     *
     * @param names 咖啡名 list
     * @return
     */
    List<Coffee> findByNameInOrderById(List<String> names);

    /**
     * 根据名字查找咖啡
     *
     * @param name 咖啡名称
     * @return
     */
    Coffee findByName(String name);
}
