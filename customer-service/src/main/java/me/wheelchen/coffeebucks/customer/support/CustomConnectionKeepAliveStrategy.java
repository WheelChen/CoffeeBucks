package me.wheelchen.coffeebucks.customer.support;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.http.HttpResponse;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;

import java.util.Arrays;

/**
 * 自定义keep live连接策略
 * @author Kelvin Chen
 * @date 2020-04-16 22:18:09
 */
public class CustomConnectionKeepAliveStrategy implements ConnectionKeepAliveStrategy {
    private final long DEFAULT_SECONDS = 30;

    @Override
    public long getKeepAliveDuration(HttpResponse httpResponse, HttpContext httpContext) {
        return Arrays.stream(httpResponse.getHeaders(HTTP.CONN_KEEP_ALIVE))
                .filter(header -> StringUtils.endsWithIgnoreCase(header.getName(), "timeout")
                        && StringUtils.isNumeric(header.getValue()))
                .findFirst()
                .map(header -> NumberUtils.toLong(header.getValue(), DEFAULT_SECONDS))
                .orElse(DEFAULT_SECONDS) * 1000;
    }
}
