package com.techidea.appclean.net;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.CertificateFactory;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by zchao on 2016/11/1.
 */

public class OkHttpManager {

    private String TAG = "OkHttpManager";

    private OkHttpClient okHttpsOneWayClient;

    private static OkHttpManager Instance;


    public OkHttpManager() {
        okHttpsOneWayClient = new OkHttpClient();
    }

    public static OkHttpManager getInstance() {
        if (Instance == null)
            Instance = new OkHttpManager();
        return Instance;
    }

    public String runHttp(String url) {
        String result = "";
        try {
            Request request = new Request.Builder()
                    .url(url)
                    .build();

            Response response = getOkHttpClient()
                    .newCall(request).execute();
            result = response.body().string();
            System.out.println(TAG + " " + result);
        } catch (IOException e) {
            System.out.println(TAG + "Exception " + e.getMessage().toString());
        }
        return result;
    }

    public String runHttpOneWay(String url) {
        String result = "";
        try {
            Request request = new Request.Builder()
                    .url(url)
                    .build();

            Response response = getOkHttpsOneWayClient()
                    .newCall(request).execute();
            result = response.body().string();
            System.out.println(TAG + " " + result);
        } catch (IOException e) {
            System.out.println(TAG + "Exception " + e.getMessage().toString());
        }
        return result;
    }

    public String runHttpBothWay(String url) {
        String result = "";
        try {
            Request request = new Request.Builder()
                    .url(url)
                    .build();

            Response response = getOkHttpsOneWayClient()
                    .newCall(request).execute();
            result = response.body().string();
            System.out.println(TAG + " " + result);
        } catch (IOException e) {
            System.out.println(TAG + "Exception " + e.getMessage().toString());
        }
        return result;
    }


    public void setCertificates(InputStream... certificates) {
        try {
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null);
            int index = 0;
            for (InputStream certificate : certificates) {
                String certificateAlias = Integer.toString(index++);
                keyStore.setCertificateEntry(certificateAlias, certificateFactory.generateCertificate(certificate));

                try {
                    if (certificate != null)
                        certificate.close();
                } catch (IOException e) {
                }
            }

            SSLContext sslContext = SSLContext.getInstance("TLS");

            TrustManagerFactory trustManagerFactory =
                    TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());

            trustManagerFactory.init(keyStore);
            sslContext.init(null,
                    trustManagerFactory.getTrustManagers(),
                    new SecureRandom());
            okHttpsOneWayClient = new OkHttpClient.Builder().sslSocketFactory(sslContext.getSocketFactory())
                    .build();
//            okHttpClient = new OkHttpClient.Builder().sslSocketFactory(null, sslContext.getSocketFactory())
//                    .build();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void setBothWayCertificates(Context context, InputStream... certificates) {
        try {
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            KeyStore serverKeyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            serverKeyStore.load(null);
            int index = 0;
            for (InputStream certificate : certificates) {
                String certificateAlias = Integer.toString(index++);
                serverKeyStore.setCertificateEntry(certificateAlias, certificateFactory.generateCertificate(certificate));

                try {
                    if (certificate != null)
                        certificate.close();
                } catch (IOException e) {
                }
            }


            SSLContext sslContext = SSLContext.getInstance("TLS");

            TrustManagerFactory trustManagerFactory =
                    TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(serverKeyStore);


            //初始化keystore
            KeyStore clientKeyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            clientKeyStore.load(context.getAssets().open("zhy_client.bks"), "123456".toCharArray());

            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            keyManagerFactory.init(clientKeyStore, "123456".toCharArray());

            sslContext.init(keyManagerFactory.getKeyManagers(),
                    trustManagerFactory.getTrustManagers(),
                    new SecureRandom());
            okHttpsOneWayClient = new OkHttpClient.Builder().sslSocketFactory(sslContext.getSocketFactory())
                    .build();
//            okHttpClient = new OkHttpClient.Builder().sslSocketFactory(null, sslContext.getSocketFactory())
//                    .build();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //单向认证
    public OkHttpClient getOkHttpsOneWayClient() {
        return okHttpsOneWayClient;
    }

    public OkHttpClient getOkHttpClient() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        return okHttpClient;
    }
}