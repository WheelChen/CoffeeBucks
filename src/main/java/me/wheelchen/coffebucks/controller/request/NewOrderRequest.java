package me.wheelchen.coffebucks.controller.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author Kelvin Chen
 * @date 2020-03-21 23:12:48
 */
@Getter
@Setter
@ToString
public class NewOrderRequest {
    private String customer;
    private List<String> items;
}
