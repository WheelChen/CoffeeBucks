package me.wheelchen.coffebucks.controller;

import lombok.extern.slf4j.Slf4j;
import me.wheelchen.coffebucks.common.exception.FormValidationException;
import me.wheelchen.coffebucks.controller.request.NewCoffeeRequest;
import me.wheelchen.coffebucks.model.Coffee;
import me.wheelchen.coffebucks.service.CoffeeService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Kelvin Chen
 * @date 2020-03-21 22:56:11
 */
@Slf4j
@RestController
@RequestMapping("/coffee")
public class CoffeeController {
    private final CoffeeService coffeeService;

    public CoffeeController(CoffeeService coffeeService) {
        this.coffeeService = coffeeService;
    }

    /**
     * 获取所有咖啡信息
     * 设置params参数, 防止与getByName接口重名
     * @return
     */
    @GetMapping(path = "/", params = "!name")
    public List<Coffee> getAll() {
        return coffeeService.findAllCoffee();
    }

    /**
     * 根据姓名查数据
     * 使用`@RequestParam`取URL中
     *
     * @param name coffee名称
     * @return
     */
    @GetMapping(path = "/", params = "name")
    public Coffee getByName(@RequestParam String name) {
        return coffeeService.getCoffee(name);
    }

    /**
     * 根据id查找咖啡
     * 使用`@PathVariable`那取URI中信息
     * @param id 咖啡id
     * @return
     */
    @GetMapping(path = "/{id}")
    public Coffee getById(@PathVariable Long id) {
        Coffee coffee = coffeeService.getCoffee(id);
        log.info("Coffee {}:", coffee);
        return coffee;
    }

    /**
     * 表单类型提交
     *
     * @param newCoffee 待创建的咖啡对象
     * @param result 运行结果
     * @return
     */
    @PostMapping(path = "/", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Coffee addCoffee(@Valid NewCoffeeRequest newCoffee,
                            BindingResult result) {
        if (result.hasErrors()) {
            // 这里先简单处理一下，后续讲到异常处理时会改
            log.warn("Binding Errors: {}", result);
            throw new FormValidationException(result);
        }
        return coffeeService.saveCoffee(newCoffee.getName(), newCoffee.getPrice());
    }

    /**
     * JSON对象提交
     *
     * @param newCoffee 待创建的咖啡对象
     * @return
     */
    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public Coffee addJsonCoffee(@Valid @RequestBody NewCoffeeRequest newCoffee,
                                                    BindingResult result) {
        if (result.hasErrors()) {
            log.warn("Binding Errors: {}", result);
            throw new ValidationException(result.toString());
        }
        return coffeeService.saveCoffee(newCoffee.getName(), newCoffee.getPrice());
    }

    /**
     * 上传文件批量增加coffee
     * 文件格式为
     *  latte 20.00
     *  mocha 18.00
     *
     * 练习使用`MultipartFile`上传文件
     *
     * @param file coffee 文本文件
     * @return
     */
    @PostMapping(path = "/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public List<Coffee> batchAddCoffee(@RequestParam("file") MultipartFile file) {
        List<Coffee> coffees = new ArrayList<>();

        if (!file.isEmpty()) {

            try (BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(file.getInputStream()))
            ) {
                String str;
                while ((str = bufferedReader.readLine()) != null) {
                    String[] arr = StringUtils.split(str, " ");
                    if (arr != null && arr.length == 2) {
                        coffees.add(coffeeService.saveCoffee(arr[0],
                                Money.of(CurrencyUnit.of("CNY"),
                                        NumberUtils.createBigDecimal(arr[1]))));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return coffees;
    }

}
