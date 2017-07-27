package com.github.shipengyan.framework.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.util.EntityUtils;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import static org.apache.commons.codec.binary.Hex.DEFAULT_CHARSET;


/**
 * http非阻塞实现
 *
 * @author shi.pengyan
 * @version 1.0 2017-07-27 13:44
 * @since 1.0
 */
@Slf4j
public final class HttpAsyncClientUtil {

    private HttpAsyncClientUtil() {
    }

    public static String get(String url) throws Exception {
        return asyncRequest("get", url, null, null);
    }

    public static String get(String url, Map<String, String> params) throws Exception {
        return asyncRequest("get", url, params, null);
    }

    public static String post(String url) throws Exception {
        return asyncRequest("post", url, null, null);
    }

    public static String post(String url, Map<String, String> params) throws Exception {
        return asyncRequest("post", url, params, null);
    }

    public static String asyncRequest(String method, String url, Map<String, String> params,
                                      Map<String, String> headers) throws Exception {

        RequestConfig requestConfig = RequestConfig.custom()
                                                   .setSocketTimeout(6000)
                                                   .setConnectTimeout(6000)
                                                   .setConnectionRequestTimeout(6000)
                                                   .build();

        CloseableHttpAsyncClient httpclient = HttpAsyncClients.custom().setDefaultRequestConfig(requestConfig).build();
        try {
            httpclient.start();

            RequestBuilder builder = RequestBuilder.create(method.toUpperCase()).setUri(url);
            builder.setCharset(Consts.UTF_8);
            builder.setConfig(requestConfig);
            if (params != null && !params.isEmpty()) {
                Set<String> keys = params.keySet();
                for (String key : keys) {
                    builder.addParameter(key, params.get(key));
                }
            }
            if (headers != null && !headers.isEmpty()) {
                Set<String> keys = headers.keySet();
                for (String key : keys) {
                    builder.addHeader(key, headers.get(key));
                }
            }

            HttpUriRequest       request  = builder.build();
            Future<HttpResponse> future   = httpclient.execute(request, null);
            HttpResponse         response = future.get(6000, TimeUnit.MILLISECONDS);
            return EntityUtils.toString(response.getEntity(), DEFAULT_CHARSET);
        } finally {
            httpclient.close();
        }
    }

    public static String request(String method, String url, Map<String, String> params,
                                 Map<String, String> headers) throws Exception {

        RequestConfig requestConfig = RequestConfig.custom()
                                                   .setSocketTimeout(6000)
                                                   .setConnectTimeout(6000)
                                                   .setConnectionRequestTimeout(6000)
                                                   .build();
        CloseableHttpClient httpclient = HttpClients.custom().setDefaultRequestConfig(requestConfig).build();
        try {
            RequestBuilder builder = RequestBuilder.create(method.toUpperCase()).setUri(url);
            builder.setCharset(Consts.UTF_8);
            builder.setConfig(requestConfig);
            if (params != null && !params.isEmpty()) {
                Set<String> keys = params.keySet();
                for (String key : keys) {
                    builder.addParameter(key, params.get(key));
                }
            }
            if (headers != null && !headers.isEmpty()) {
                Set<String> keys = headers.keySet();
                for (String key : keys) {
                    builder.addHeader(key, headers.get(key));
                }
            }

            HttpUriRequest        request  = builder.build();
            CloseableHttpResponse response = httpclient.execute(request);
            return EntityUtils.toString(response.getEntity(), DEFAULT_CHARSET);
        } finally {
            httpclient.close();
        }
    }
}

