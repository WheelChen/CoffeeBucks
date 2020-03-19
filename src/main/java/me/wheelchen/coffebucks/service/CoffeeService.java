package me.wheelchen.coffebucks.service;

import lombok.extern.slf4j.Slf4j;
import me.wheelchen.coffebucks.model.Coffee;
import me.wheelchen.coffebucks.model.CoffeeCache;
import me.wheelchen.coffebucks.repository.CoffeeCacheRepository;
import me.wheelchen.coffebucks.repository.CoffeeRepository;
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
public class CoffeeService {

    private final CoffeeCacheRepository coffeeCacheRepository;

    private final CoffeeRepository coffeeRepository;

    public CoffeeService(CoffeeRepository coffeeRepository, CoffeeCacheRepository coffeeCacheRepository) {
        this.coffeeRepository = coffeeRepository;
        this.coffeeCacheRepository = coffeeCacheRepository;
    }


    public List<Coffee> findAllCoffee() {
        return coffeeRepository.findAll();
    }

    public Optional<Coffee> findOneByName(String name) {
        return coffeeRepository.findOneByName(name);
    }

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
     * @param name
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
}

