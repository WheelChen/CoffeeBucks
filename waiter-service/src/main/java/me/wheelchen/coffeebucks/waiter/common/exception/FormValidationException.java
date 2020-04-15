package me.wheelchen.coffeebucks.waiter.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 表单异常处理类
 *
 * @author Kelvin Chen
 * @date 2020-04-02 22:59:24
 */
@Getter
@AllArgsConstructor
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FormValidationException extends RuntimeException {
    private BindingResult result;
}
