package me.wheelchen.coffebucks.converter;

import org.joda.money.Money;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

import java.nio.charset.StandardCharsets;


/**
 * 将Money类型转为字节数组
 * 方便存入Redis中
 * @author Kelvin Chen
 * @date 2020-03-19 17:54:40
 */
@WritingConverter
public class MoneyToBytesConverter implements Converter<Money, byte[]> {

    @Override
    public byte[] convert(Money money) {
        String value = Long.toString(money.getAmountMajorLong());
        return value.getBytes(StandardCharsets.UTF_8);
    }
}
