package me.wheelchen.coffebucks.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.money.Money;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

/**
 * 存入缓存中的咖啡商品信息
 * `@RedisHash` - 设置该对象以Hash类型存入Redis
 *
 * @author Kelvin Chen
 * @date 2020-03-19 16:28:45
 */
@RedisHash(value = "bucks-coffee", timeToLive = 60)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CoffeeCache {
    /**
     * id
     */
    @Id
    private Long id;
    /**
     * `@Indexed` 设置为索引
     * 咖啡名
     */
    @Indexed
    private String name;

    /**
     * 价格
     */
    private Money price;
}
