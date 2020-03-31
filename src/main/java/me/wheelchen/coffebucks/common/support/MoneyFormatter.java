package me.wheelchen.coffebucks.common.support;

import org.apache.commons.lang3.StringUtils;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;
import org.apache.commons.lang3.math.NumberUtils;

import java.text.ParseException;
import java.util.Locale;

/**
 * @author Kelvin Chen
 * @date 2020-03-30 22:45:05
 */
@Component
public class MoneyFormatter implements Formatter<Money> {

    /**
     * 处理 CNY 10.00 / 10.00
     * @param text 金额文本
     * @param locale 地区信息
     * @return money类型
     * @throws ParseException
     */
    @Override
    public Money parse(String text, Locale locale) throws ParseException {
        // 若能被解析为数字 如：10.00
        if (NumberUtils.isParsable(text)) {
            return Money.of(CurrencyUnit.of("CNY"), NumberUtils.createBigDecimal(text));
        } else if (StringUtils.isNotEmpty(text)) {
            String[] split = StringUtils.split(text, " ");
            if (split != null && split.length == 2 && NumberUtils.isParsable(split[1])) {
                return Money.of(CurrencyUnit.of(split[0]),
                        NumberUtils.createBigDecimal(split[1]));
            } else {
                throw new ParseException(text, 0);
            }
        }
        throw new ParseException(text, 0);
    }

    @Override
    public String print(Money money, Locale locale) {
        if (money == null) {
            return null;
        }
        return money.getCurrencyUnit().getCode() + " " + money.getAmount();
    }
}
