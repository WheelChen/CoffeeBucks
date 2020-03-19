package me.wheelchen.coffebucks.model;

/**
 * @author Kelvin Chen
 * @date 2020-03-19 10:55:48
 */
public enum  OrderState {
    /**
     * 初始化
     */
    INIT,
    /**
     * 已支付
     */
    PAID,
    /**
     * 制作中
     */
    BREWING,
    /**
     * 制作完成
     */
    BREWED,
    /**
     * 已取走
     */
    TAKEN,
    /**
     * 取消订单
     */
    CANCELLED
}
