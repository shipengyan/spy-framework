package com.github.shipengyan.framework.http;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.github.shipengyan.framework.common.Const.DEFAULT_CHARTSET;

/**
 * 模块名
 *
 * @author spy
 * @version 1.0 2018/10/16
 * @since 1.0
 */
@Slf4j
public class HttpClientPoolUtil {
    // 单位毫秒
    // setConnectTimeout：设置连接超时时间，。
    // setConnectionRequestTimeout：设置从connect Manager获取Connection 超时时间。这个属性是新加的属性，因为目前版本是可以共享连接池的。
    // setSocketTimeout：请求获取数据的超时时间。如果访问一个接口，多少时间内无法返回数据，就直接放弃此次调用。

    // 连接超时时间,毫秒
    private static final int CONNECT_TIMEOUT = 10000;

    // 从http连接池获取Connection 超时时间，毫秒
    private static final int CONNECT_REQUEST_TIMEOUT = 10000;

    // socket timeout
    private static final int DEFAULT_SOCKET_TIMEOUT = 10 * 1000;


    /**
     * get
     *
     * @param url
     * @return
     * @throws Exception
     */
    public static String get(String url) throws Exception {
        return get(url, null);
    }

    public static String get(String url, Map<String, Object> paramMap) throws Exception {
        return get(url, paramMap, null);
    }

    /**
     * get
     *
     * @param url
     * @param paramMap
     * @return
     * @throws Exception
     */
    public static String get(String url, Map<String, Object> paramMap, RequestConfig requestConfig) throws Exception {

        if (requestConfig == null) {
            requestConfig = RequestConfig.custom()
                                         .setConnectTimeout(CONNECT_TIMEOUT)
                                         .setConnectionRequestTimeout(CONNECT_REQUEST_TIMEOUT)
                                         .setSocketTimeout(DEFAULT_SOCKET_TIMEOUT)
                                         .build();
        }


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

        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(requestConfig);

        CloseableHttpResponse response;
        CloseableHttpClient httpClient = HttpClientPool.getHttpClient();

        try {
            response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String entityStr = EntityUtils.toString(entity, DEFAULT_CHARTSET);
                return entityStr;
            }
        } finally {

            if (httpGet != null) {
                httpGet.releaseConnection();
            }
        }

        return null;
    }


    /**
     * post
     *
     * @param url
     * @return
     * @throws Exception
     */
    public static String post(String url) throws Exception {

        return post(url, null);
    }


    public static String post(String url, Map<String, String> paramMap) throws Exception {
        return post(url, paramMap, null);
    }

    /**
     * post
     *
     * @param url
     * @param paramMap
     * @return
     * @throws Exception
     */
    public static String post(String url, Map<String, String> paramMap, RequestConfig requestConfig) throws Exception {

        if (requestConfig == null) {
            requestConfig = RequestConfig.custom()
                                         .setConnectTimeout(CONNECT_TIMEOUT)
                                         .setConnectionRequestTimeout(CONNECT_REQUEST_TIMEOUT)
                                         .setSocketTimeout(DEFAULT_SOCKET_TIMEOUT)
                                         .build();
        }

        List<NameValuePair> formParams = null;
        if (paramMap != null) {
            formParams = new ArrayList<>(); //创建参数队列
            for (Map.Entry<String, String> entry : paramMap.entrySet()) {
                formParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        }

        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(requestConfig);


        CloseableHttpClient httpClient = HttpClientPool.getHttpClient();
        //log.debug("HttpClientUtil->" + "执行请求," + httppost.getURI() + " reqJson= " + JSON.toJSONString(reqMap));

        // response、httpclient 使用之后不释放，实现复用
        try {
            if (formParams != null) {
                httpPost.setEntity(new UrlEncodedFormEntity(formParams, DEFAULT_CHARTSET));
            }

            CloseableHttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            String respStr = EntityUtils.toString(entity, DEFAULT_CHARTSET);

            return respStr;

        } catch (ConnectTimeoutException | SocketTimeoutException | ClientProtocolException | SocketException e) {
            // log.error("HttpClientUtil->" + "连接服超时，通信异常", e);
            throw e;
        } catch (IOException e) {
            // log.error("HttpClientUtil->" + "发送http请求异常", e);
            throw e;
        } finally {
            httpPost.releaseConnection();
        }
    }


    /**
     * post JSON
     *
     * @param url
     * @return
     * @throws Exception
     */
    public static String postJSON(String url) throws Exception {
        return post(url, null);
    }

    /**
     * post JSON
     *
     * @param url
     * @param paramMap
     * @return
     * @throws Exception
     */

    public static String postJSON(String url, Map<String, Object> paramMap) throws Exception {
        return postJSON(url, paramMap, null);
    }

    /**
     * 发送http请求
     *
     * @param url           http请求的url
     * @param reqMap        http请求的map
     * @param requestConfig
     * @return 调用返回的map
     * @throws Exception 调用的异常
     */
    public static String postJSON(String url, Map<String, Object> reqMap, RequestConfig requestConfig) throws Exception {

        if (requestConfig == null) {
            requestConfig = RequestConfig.custom()
                                         .setConnectTimeout(CONNECT_TIMEOUT)
                                         .setConnectionRequestTimeout(CONNECT_REQUEST_TIMEOUT)
                                         .setSocketTimeout(DEFAULT_SOCKET_TIMEOUT)
                                         .build();
        }

        StringEntity stringEntity = new StringEntity(JSON.toJSONString(reqMap), "UTF-8");
        stringEntity.setContentEncoding("UTF-8");
        stringEntity.setContentType("application/json");


        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(requestConfig);
        httpPost.setEntity(stringEntity);

        CloseableHttpClient httpClient = HttpClientPool.getHttpClient();
        //log.debug("HttpClientUtil->" + "执行请求," + httppost.getURI() + " reqJson= " + JSON.toJSONString(reqMap));

        // response、httpclient 使用之后不释放，实现复用
        try {
            CloseableHttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            String respStr = EntityUtils.toString(entity, "UTF-8");

            return respStr;

            // log.debug("HttpClientUtil->" + "收到请求返回的结果" + respStr);
//            Map<String, Object> respMap;
//            respMap = (Map<String, Object>) JSON.parseObject(respStr, Map.class);
//            return respMap;

        } catch (ConnectTimeoutException | SocketTimeoutException | ClientProtocolException | SocketException e) {
            // log.error("HttpClientUtil->" + "连接服超时，通信异常", e);
            throw e;
        } catch (IOException e) {
            // log.error("HttpClientUtil->" + "发送http请求异常", e);
            throw e;
        } finally {
            httpPost.releaseConnection();
        }
    }
}
