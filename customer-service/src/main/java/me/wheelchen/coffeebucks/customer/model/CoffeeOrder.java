package me.wheelchen.coffeebucks.customer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 咖啡订单表
 * @author wheelchen
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CoffeeOrder {
    private Long id;
    private String customer;
    private OrderState state;
    private Date createTime;
    private Date updateTime;
}
