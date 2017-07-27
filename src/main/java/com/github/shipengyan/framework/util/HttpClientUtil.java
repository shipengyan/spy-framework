package com.github.shipengyan.framework.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.github.shipengyan.framework.common.Const.DEFAULT_CHARTSET;

/**
 * 模块名
 *
 * @author shi.pengyan
 * @version 1.0 2017-07-27 11:19
 * @since 1.0
 */
@Slf4j
public final class HttpClientUtil {


    private HttpClientUtil() {

    }

    /**
     * get method
     *
     * @param url
     * @return
     */
    public static String get(String url) throws IOException {
        return get(url, null);
    }

    /**
     * http get request
     *
     * @param url
     * @param paramMap
     * @return
     */
    public static String get(String url, Map<String, String> paramMap) throws IOException {
        HttpClientBuilder   builder    = HttpClientBuilder.create();
        CloseableHttpClient httpClient = builder.build();

        if (paramMap != null) {
            StringBuilder sb = new StringBuilder();
            paramMap.forEach((key, value) -> {
                sb.append(key).append("=").append(value).append("&");
            });

            if (url.indexOf("?") == -1) {
                url = url + "?" + sb.toString();
            } else {
                url = url + "&" + sb.toString();
            }
        }
        log.info("get request url={}", url);

        //执行
        HttpUriRequest httpGet = new HttpGet(url);

        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String entityStr = EntityUtils.toString(entity, DEFAULT_CHARTSET);
                return entityStr;
            }
        } finally {

            HttpClientUtils.closeQuietly(response);
            HttpClientUtils.closeQuietly(httpClient);
        }

        return null;
    }

    /**
     * post request
     *
     * @param url
     * @return
     */
    public static String post(String url) throws IOException {
        return post(url, null);
    }

    /**
     * post request
     *
     * @param url
     * @param paramMap
     * @return
     */
    public static String post(String url, Map<String, String> paramMap) throws IOException {
        HttpClientBuilder   builder    = HttpClientBuilder.create();
        CloseableHttpClient httpClient = builder.build();

        log.info("post request url={}", url);

        HttpPost httpPost = new HttpPost(url);

        List<NameValuePair> formParams = null;
        if (paramMap != null) {
            formParams = new ArrayList<>(); //创建参数队列
            for (Map.Entry<String, String> entry : paramMap.entrySet()) {
                formParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        }


        CloseableHttpResponse response = null;
        try {
            if (formParams != null) {
                httpPost.setEntity(new UrlEncodedFormEntity(formParams, DEFAULT_CHARTSET));
            }

            response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            if (null != entity) {
                String responseContent = EntityUtils.toString(entity, DEFAULT_CHARTSET);
                return responseContent;
            }

        } finally {

            if (httpPost != null) {
                httpPost.releaseConnection();
            }
            HttpClientUtils.closeQuietly(response);
            HttpClientUtils.closeQuietly(httpClient);

        }
        return null;
    }

    /**
     * post send JSON data
     *
     * @param url
     * @param body
     * @return
     * @throws Exception
     */
    public static String postJSON(String url, String body) throws Exception {
        HttpClientBuilder   builder    = HttpClientBuilder.create();
        CloseableHttpClient httpClient = builder.build();

        HttpPost              post     = null;
        String                resData  = null;
        CloseableHttpResponse response = null;
        try {
            post = new HttpPost(url);
            HttpEntity entity2 = new StringEntity(body, Consts.UTF_8);
            post.setConfig(RequestConfig.custom().setConnectTimeout(30000).setSocketTimeout(30000).build());
            post.setHeader("Content-Type", "application/json");
            post.setEntity(entity2);
            response = httpClient.execute(post);
            if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
                resData = EntityUtils.toString(response.getEntity());
            }
        } finally {

            if (post != null) {
                post.releaseConnection();
            }

            HttpClientUtils.closeQuietly(response);
            HttpClientUtils.closeQuietly(httpClient);
        }
        return resData;
    }


}
