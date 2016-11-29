package com.techidea.appclean.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by zchao on 2016/11/2.
 */

public class HttpUrlConnectManager {

    private void httpurl() {
        HttpURLConnection connection = null;
        OutputStreamWriter wr = null;

        BufferedReader rd = null;

        StringBuilder sb = new StringBuilder();

        String line = null;

        URL serverAddress = null;

        try {

            serverAddress = new URL("https://kyfw.12306.cn/otn/");

            //1) open connection

            connection = (HttpURLConnection) serverAddress.openConnection();

            //2) connection settings

            connection.setRequestMethod("GET");

            connection.setDoOutput(true);

            connection.setReadTimeout(10000);

//3)connect

            connection.connect();

            //4)write data

            // get the output stream writer and write the output to the server

            // not needed in this example

            // wr = new OutputStreamWriter(connection.getOutputStream());

            // wr.write("....");

            // wr.flush();


            //5)read the result from the server

            rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            while ((line = rd.readLine()) != null) {

                sb.append(line + '\n');

            }

            System.out.println(sb.toString());

            //6)close

            rd.close();


        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (ProtocolException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        } finally {

        }

    }
}
