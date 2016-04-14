package http;

import Utils.JsonUtils;
import log.LogKit;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yhx on 2016/4/11.
 */
public class CommonHttpClient {

    private static final int CONNECTION_TIMEOUT = 2 * 1000;
    private static final int SO_TIMEOUT = 2 * 1000;
    private static final RequestConfig HttpRequestConfig =
            RequestConfig.custom().setConnectTimeout(CONNECTION_TIMEOUT).setSocketTimeout(SO_TIMEOUT).build();
    private static final String HttpContentType = ContentType.APPLICATION_JSON.getMimeType();

    private static Map<String, String> newHeaderMap(String contentType, String cookieValue) {
        Map<String, String> headerMap = new HashMap<String, String>();
        headerMap.put("Content-Type", contentType);
        headerMap.put("Cookie", cookieValue);
        return headerMap;
    }


    public static String doGet(String url, String cookieValue, Map<String, ?> paramMap) {
        return doGet(url, newHeaderMap(HttpContentType, cookieValue), paramMap, HttpRequestConfig);
    }
    public static String doGet(String url, Map<String, String> headerMap, Map<String, ?> paramMap, RequestConfig requestConfig) {
        String getUrl = newUrl(url, paramMap);
        if (LogKit.isDebugEnabled()) {
            LogKit.debug("httpGet url:" + getUrl);
        }

        long startTime = System.currentTimeMillis();
        CloseableHttpClient client = HttpClients.createDefault();
        try {
            HttpGet httpGet = new HttpGet(getUrl);
            httpGet.setConfig(requestConfig);
            for(Map.Entry<String, String> headerEntry : headerMap.entrySet()) {
                httpGet.addHeader(headerEntry.getKey(), headerEntry.getValue());
            }

            CloseableHttpResponse response = client.execute(httpGet);
            try {
                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode != HttpStatus.SC_OK) {
                    LogKit.warn("httpGet warn, response statusCode:" + statusCode + ", url" + url);
                }
                if (statusCode >= HttpStatus.SC_OK && statusCode <= HttpStatus.SC_INSUFFICIENT_STORAGE) {
                    HttpEntity entity = response.getEntity();
                    return entity != null ? EntityUtils.toString(entity, Consts.UTF_8) : null;
                } else {
                    throw new ClientProtocolException("Unexpected response statusCode: " + statusCode);
                }
            } finally {
                response.close();
            }
        } catch (IOException e) {
            LogKit.error("httpGet error, message:" + e.toString() + ", url" + url);
        } finally {
            try {
                client.close();
            } catch (IOException e) {
                LogKit.error("httpGet client close error, url" + url);
            }
            long useTime = System.currentTimeMillis() - startTime;
            if(useTime > LogKit.Default_Slow_Time){
                LogKit.warn("httpGet tooSlow, useTime:" + useTime + ", url" + url);
            }
        }
        return null;
    }
    private static String newUrl(String url, Map<String, ?> paramMap) {
        if (paramMap == null || paramMap.size() == 0) {
            return url;
        }
        StringBuilder sb = new StringBuilder(url);
        if (url.indexOf("?") == -1 && url.indexOf("&") == -1) {
            sb.append("?");
        } else {
            sb.append("&");
        }
        int i = 0;
        for (String key : paramMap.keySet()) {
            try {
                String value = (String) paramMap.get(key);
                if (i > 0) {
                    sb.append("&");
                }
                sb.append(URLEncoder.encode(key, "UTF-8"));
                sb.append("=");
                sb.append(URLEncoder.encode(value, "UTF-8"));
                i++;
            } catch (Exception e) {
                LogKit.error("encode paramMap error,", e);
            }
        }
        return sb.toString();
    }

    public static String doPost(String url, String cookieValue, Map<String, ?> paramMap) {
        List<NameValuePair> list = new ArrayList<NameValuePair>();
        for (Map.Entry<String, ?> entry : paramMap.entrySet()) {
            list.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
        }
        return doPost(url, newHeaderMap(HttpContentType, cookieValue), new UrlEncodedFormEntity(list, Consts.UTF_8), HttpRequestConfig);
    }
    public static String doPostJson(String url, String cookieValue, Map<String, ?> paramMap) {
        return doPost(url, cookieValue, JsonUtils.toJson(paramMap));
    }
    public static String doPost(String url, String cookieValue, String str) {
        return doPost(url, newHeaderMap(HttpContentType, cookieValue), new StringEntity(str, Consts.UTF_8), HttpRequestConfig);
    }
    public static String doPost(String url, Map<String, String> headerMap, HttpEntity httpEntity, RequestConfig requestConfig) {
        long startTime = System.currentTimeMillis();
        CloseableHttpClient client = HttpClients.createDefault();
        try {
            HttpPost httpPost = new HttpPost(url);
            httpPost.setConfig(requestConfig);
            for(Map.Entry<String, String> headerEntry : headerMap.entrySet()) {
                httpPost.addHeader(headerEntry.getKey(), headerEntry.getValue());
            }
            httpPost.setEntity(httpEntity);
            if (LogKit.isDebugEnabled()) {
                LogKit.debug("httpPost url:" + url + ", entity:" + EntityUtils.toString(httpEntity));
            }

            CloseableHttpResponse response = client.execute(httpPost);
            try {
                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode != HttpStatus.SC_OK) {
                    LogKit.warn("httpGet warn, response statusCode:" + statusCode + ", url" + url);
                }
                if (statusCode >= HttpStatus.SC_OK && statusCode <= HttpStatus.SC_INSUFFICIENT_STORAGE) {
                    HttpEntity entity = response.getEntity();
                    return entity != null ? EntityUtils.toString(entity, Consts.UTF_8) : null;
                } else {
                    throw new ClientProtocolException("Unexpected response statusCode: " + statusCode);
                }
            } finally {
                response.close();
            }
        } catch (IOException e) {
            LogKit.error("httpPost error, message:" + e.toString() + ", url" + url);
        } finally {
            try {
                client.close();
            } catch (IOException e) {
                LogKit.error("httpPost client close error, url" + url);
            }
            long useTime = System.currentTimeMillis() - startTime;
            if(useTime > LogKit.Default_Slow_Time){
                LogKit.warn("httpPost tooSlow, useTime:" + useTime + ", url" + url);
            }
        }
        return null;
    }

}
