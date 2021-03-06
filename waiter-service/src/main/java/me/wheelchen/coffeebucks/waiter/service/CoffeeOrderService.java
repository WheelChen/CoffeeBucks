package me.wheelchen.coffeebucks.waiter.service;

import lombok.extern.slf4j.Slf4j;
import me.wheelchen.coffeebucks.waiter.model.Coffee;
import me.wheelchen.coffeebucks.waiter.model.CoffeeOrder;
import me.wheelchen.coffeebucks.waiter.model.OrderState;
import me.wheelchen.coffeebucks.waiter.repository.CoffeeOrderRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * 咖啡订单操作
 *
 * @author Kelvin Chen
 * @date 2020-03-19 11:20:48
 */
@Slf4j
@Service
@Transactional
public class CoffeeOrderService {
    private final CoffeeOrderRepository coffeeOrderRepository;

    /**
     * 自动注入
     * @param coffeeOrderRepository 咖啡订单相关Dao操作
     */
    public CoffeeOrderService(CoffeeOrderRepository coffeeOrderRepository) {
        this.coffeeOrderRepository = coffeeOrderRepository;
    }

    /**
     * 创建订单
     * @param customer 顾客名
     * @param coffee 咖啡（可变参数）
     * @return 订单对象
     */
    public CoffeeOrder createOrder(String customer, Coffee... coffee) {
        CoffeeOrder order = CoffeeOrder.builder()
                .customer(customer)
                .items(new ArrayList<>(Arrays.asList(coffee)))
                .state(OrderState.INIT)
                .build();
        CoffeeOrder saved = coffeeOrderRepository.save(order);
        log.info("New Order: {}", saved);
        return saved;
    }

    /**
     * 更新状态
     * @param order 订单信息
     * @param state 订单状态
     * @return 更新是否成功
     */
    public boolean updateState(CoffeeOrder order, OrderState state) {
        if (state.compareTo(order.getState()) <= 0) {
            log.warn("Wrong State order: {}, {}", state, order.getState());
            return false;
        }
        order.setState(state);
        coffeeOrderRepository.save(order);
        log.info("Updated Order: {}", order);
        return true;
    }

    /**
     * 根据订单id获取订单
     * @param id 订单id
     * @return
     */
    public CoffeeOrder get(Long id) {
        return coffeeOrderRepository.getOne(id);
    }
}
