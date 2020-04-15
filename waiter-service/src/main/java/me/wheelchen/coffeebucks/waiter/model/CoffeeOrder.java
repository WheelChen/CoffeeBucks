package me.wheelchen.coffeebucks.waiter.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author Kelvin Chen
 * @date 2020-03-19 10:48:32
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "T_ORDER")
public class CoffeeOrder extends BaseEntity implements Serializable {

    /**
     * 顾客名
     */
    private String customer;

    /**
     * 订单中的咖啡信息
     */
    @ManyToMany
    @JoinTable(name = "T_ORDER_COFFEE")
    @OrderBy("id")
    private List<Coffee> items;

    /**
     * 订单状态
     */
    @Enumerated
    @Column(nullable = false)
    private OrderState state;
}
