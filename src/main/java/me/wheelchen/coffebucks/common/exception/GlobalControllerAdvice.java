package me.wheelchen.coffebucks.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ValidationException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Kelvin Chen
 * @date 2020-04-02 23:16:28
 */
@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> validationExceptionHandler(ValidationException exception) {
        Map<String, String> map = new HashMap<>(16);
        map.put("message", exception.getMessage());
        return map;
    }
}
