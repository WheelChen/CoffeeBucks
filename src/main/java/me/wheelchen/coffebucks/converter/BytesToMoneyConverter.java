package me.wheelchen.coffebucks.converter;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

import java.nio.charset.StandardCharsets;


/**
 * 将字节转为Money类型
 * 
 * @author Kelvin Chen
 * @date 2020-03-19 18:09:21
 */
@ReadingConverter
public class BytesToMoneyConverter implements Converter<byte[], Money> {
    @Override
    public Money convert(byte[] bytes) {
        String value = new String(bytes, StandardCharsets.UTF_8);
        return Money.of(CurrencyUnit.of("CNY"), Long.parseLong(value));
    }
}
