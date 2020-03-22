package me.wheelchen.coffebucks.service;

import lombok.extern.slf4j.Slf4j;
import me.wheelchen.coffebucks.model.Coffee;
import me.wheelchen.coffebucks.model.CoffeeCache;
import me.wheelchen.coffebucks.repository.CoffeeCacheRepository;
import me.wheelchen.coffebucks.repository.CoffeeRepository;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;


/**
 * 咖啡表操作
 *
 * @author Kelvin Chen
 * @date 2020-03-19 11:09:57
 */
@Slf4j
@Service
@CacheConfig(cacheNames = "CoffeeCache")
public class CoffeeService {

    private final CoffeeCacheRepository coffeeCacheRepository;

    private final CoffeeRepository coffeeRepository;

    /**
     * 构造器方式注入Bean
     *
     * @param coffeeRepository 咖啡表相关操作
     * @param coffeeCacheRepository 咖啡缓存操作
     */
    public CoffeeService(CoffeeRepository coffeeRepository, CoffeeCacheRepository coffeeCacheRepository) {
        this.coffeeRepository = coffeeRepository;
        this.coffeeCacheRepository = coffeeCacheRepository;
    }

    /**
     * 获取所有咖啡信息
     *
     * @return
     */
    @Cacheable
    public List<Coffee> findAllCoffee() {
        return coffeeRepository.findAll();
    }

    /**
     * 根据咖啡名称查找咖啡信息
     *
     * @param name 咖啡名称
     * @return
     */
    public Optional<Coffee> findOneByName(String name) {
        return coffeeRepository.findOneByName(name);
    }

    /**
     * 优先从缓存中获取咖啡信息
     * 若无则访问数据库再存入缓存
     *
     * @param name 咖啡名称
     * @return
     */
    public Optional<Coffee> findSimpleCoffeeFromCache(String name) {
        Optional<CoffeeCache> cached = coffeeCacheRepository.findOneByName(name);

        if (cached.isPresent()) {
            // 若缓存中存在
            CoffeeCache coffeeCache = cached.get();
            Coffee coffee = Coffee.builder()
                    .name(coffeeCache.getName())
                    .price(coffeeCache.getPrice())
                    .build();
            log.info("Coffee {} found from cache.", coffeeCache);
            return Optional.of(coffee);
        } else {
            // 若不存在则去数据库中查出并放入缓存
            Optional<Coffee> raw = findOneCoffee(name);
            raw.ifPresent(c -> {
                CoffeeCache coffeeCache = CoffeeCache.builder()
                        .id(c.getId())
                        .name(c.getName())
                        .price(c.getPrice())
                        .build();
                log.info("Save Coffee {} to cache.", coffeeCache);
                coffeeCacheRepository.save(coffeeCache);
            });
            return raw;
        }


    }

    /**
     * 使用ExampleMatcher匹配
     * 根据咖啡名称忽略大小写匹配
     * @param name 咖啡名称
     * @return
     */
    public Optional<Coffee> findOneCoffee(String name) {
        // 创建匹配器
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("name", exact().ignoreCase());
        Example<Coffee> coffeeExample = Example.of(Coffee.builder().name(name).build(), matcher);

        // 查找
        Optional<Coffee> coffee = coffeeRepository.findOne(coffeeExample);
        log.info("Coffee Found: {}", coffee);
        return coffee;
    }

    /**
     * 根据咖啡名查找、按id排序
     *
     * @param names 咖啡名 list
     * @return
     */
    public List<Coffee> getCoffeeByName(List<String> names) {
        return coffeeRepository.findByNameInOrderById(names);
    }
}

