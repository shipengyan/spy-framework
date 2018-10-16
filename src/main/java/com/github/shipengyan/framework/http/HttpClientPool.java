package com.github.shipengyan.framework.http;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;

/**
 * HttpClient池化
 *
 * @author spy
 * @version 1.0 2018/10/16
 * @since 1.0
 */
@Slf4j
public class HttpClientPool {

    // thread safe
    private static CloseableHttpClient httpClient = null;


    /**
     * 获取一个复用的池
     *
     * @return http
     */
    public static synchronized CloseableHttpClient getHttpClient() {

        return get(200, 10);
    }


    private static CloseableHttpClient get(Integer maxTotal, Integer maxPerRoute) {
        if (httpClient == null) {
            PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();

            // 连接池最大连接数
            cm.setMaxTotal(maxTotal);
            // 单条链路最大连接数（一个ip+一个端口 是一个链路）
            cm.setDefaultMaxPerRoute(maxPerRoute);
            // 指定某条链路的最大连接数

            ConnectionKeepAliveStrategy kaStrategy = new DefaultConnectionKeepAliveStrategy() {
                @Override
                public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
                    long keepAlive = super.getKeepAliveDuration(response, context);
                    if (keepAlive == -1) {
                        keepAlive = 60000;
                    }
                    return keepAlive;
                }

            };

            httpClient = HttpClients.custom()
                                    .setConnectionManager(cm)
                                    .setKeepAliveStrategy(kaStrategy)
                                    .build();
        }

        return httpClient;
    }
}
