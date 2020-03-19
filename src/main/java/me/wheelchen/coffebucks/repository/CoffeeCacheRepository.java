package me.wheelchen.coffebucks.repository;

import me.wheelchen.coffebucks.model.CoffeeCache;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * @author Kelvin Chen
 * @date 2020-03-19 17:25:03
 */
public interface CoffeeCacheRepository extends CrudRepository<CoffeeCache, Long> {
    /**
     * 根据咖啡名从缓存中查找
     *
     * @param name 咖啡名
     * @return 咖啡信息
     */
    Optional<CoffeeCache> findOneByName(String name);
}
