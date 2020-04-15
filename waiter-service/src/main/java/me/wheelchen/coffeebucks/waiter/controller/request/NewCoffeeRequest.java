package me.wheelchen.coffeebucks.waiter.controller.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.joda.money.Money;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author Kelvin Chen
 * @date 2020-03-30 22:59:35
 */
@Getter
@Setter
@ToString
public class NewCoffeeRequest {

    @NotEmpty
    private String name;

    @NotNull
    private Money price;

}
