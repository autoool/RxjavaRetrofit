package com.techidea.appclean.net;

import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by zchao on 2016/11/2.
 */

public class HttpClientManager {
//    HttpClient

    private String TAG = "HttpClientManager";
    private HttpClient httpClient;
    private HttpParams httpParams;


    public HttpClientManager() {
//        this.httpParams = new BasicHttpParams();
    }

    public String httpClientGet(String url) {
        String result = "";
        HttpGet httpGet = new HttpGet(url);
        try {
            HttpResponse httpResponse = getHttpClient().execute(httpGet);
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                result = EntityUtils.toString(httpResponse.getEntity());
            } else {
                result = "Error Response" + httpResponse.getStatusLine().toString();
            }
        } catch (Exception e) {
            result = e.getMessage().toString();
        }
        System.out.println(result);
        Log.v(TAG, result);
        return result;
    }

    public String httpClientPost(String url, List<NameValuePair> params) {
        HttpPost httpPost = new HttpPost(url);
        String result = "";
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            HttpResponse httpResponse = httpClient.execute(httpPost);
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                result = EntityUtils.toString(httpResponse.getEntity());
            } else {
                result = "Error Response: " +
                        httpResponse.getStatusLine().toString();
            }
        } catch (Exception e) {
            result = e.getMessage().toString();
        }
        System.out.println(result);
        Log.v(TAG, result);
        return result;
    }

    public void httpsClientGet(String url) {
        String result = "";
        HttpGet httpGet = new HttpGet(url);
        try {
            HttpResponse httpResponse = getHttpsOneWayClient().execute(httpGet);
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                result = EntityUtils.toString(httpResponse.getEntity());
            } else {
                result = "Error Response" + httpResponse.getStatusLine().toString();
            }
        } catch (Exception e) {
            result = e.getMessage().toString();
        }
        System.out.println(result);
        Log.v(TAG, result);
    }

    public void httpsClientPost(String url, List<NameValuePair> params) {
        HttpPost httpPost = new HttpPost(url);
        String result = "";
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            HttpResponse httpResponse = getHttpsOneWayClient().execute(httpPost);
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                result = EntityUtils.toString(httpResponse.getEntity());
            } else {
                result = "Error Response: " +
                        httpResponse.getStatusLine().toString();
            }
        } catch (Exception e) {
            result = e.getMessage().toString();
        }
        System.out.println(result);
        Log.v(TAG, result);
    }

    public void httpssClientGet(String url) {
        String result = "";
        HttpGet httpGet = new HttpGet(url);
        try {
            HttpResponse httpResponse = getHttpsBothWayClient().execute(httpGet);
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                result = EntityUtils.toString(httpResponse.getEntity());
            } else {
                result = "Error Response" + httpResponse.getStatusLine().toString();
            }
        } catch (Exception e) {
            result = e.getMessage().toString();
        }
        System.out.println(result);
        Log.v(TAG, result);
    }

    public void httpssClientPost(String url, List<NameValuePair> params) {
        HttpPost httpPost = new HttpPost(url);
        String result = "";
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            HttpResponse httpResponse = getHttpsBothWayClient().execute(httpPost);
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                result = EntityUtils.toString(httpResponse.getEntity());
            } else {
                result = "Error Response: " +
                        httpResponse.getStatusLine().toString();
            }
        } catch (Exception e) {
            result = e.getMessage().toString();
        }
        System.out.println(result);
        Log.v(TAG, result);
    }

    private HttpClient getHttpClient() {

//        设置连接超时和 Socket 超时，以及 Socket 缓存大小
//        HttpConnectionParams.setConnectionTimeout(httpParams, 30 * 1000);
//        HttpConnectionParams.setSoTimeout(httpParams, 20 * 1000);
//        HttpConnectionParams.setSocketBufferSize(httpParams, 8192);
//        //设置重定向，缺省为 true
//        HttpClientParams.setRedirecting(httpParams, true);
//        //user agent
//        String userAgent = "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2) Gecko/20100115 Firefox/3.6";
//        HttpProtocolParams.setUserAgent(httpParams, userAgent);
        httpClient = new DefaultHttpClient();
//        httpClient = new DefaultHttpClient(httpParams);
        return httpClient;
    }


    //默認 使用系統承認的商業證書

    private HttpClient getHttpsOneWayClient() {
        httpClient = new DefaultHttpClient();
        return httpClient;
    }

    private HttpClient getHttpsBothWayClient() {
        httpClient = new DefaultHttpClient();
        return httpClient;
    }
}
