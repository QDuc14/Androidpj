package com.example.money;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class ServiceCaller {
    public final static String GET = "GET";
    public final static String POST = "POST";

    public String call(String method, String url) {
        InputStream in = null;
        BufferedReader br= null;

        try {
            URL urlConn = new URL(url);
            HttpURLConnection httpConn = (HttpURLConnection)urlConn.openConnection();
            httpConn.setRequestMethod(method);
            httpConn.connect();
            int resCode = httpConn.getResponseCode();

            if (resCode == HttpURLConnection.HTTP_OK) {
                in = httpConn.getInputStream();
                br= new BufferedReader(new InputStreamReader(in, "UTF-8"));

                StringBuilder sb= new StringBuilder();
                String s= null;
                while((s= br.readLine())!= null) {
                    sb.append(s);
                }
                return sb.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("My error", e.toString());
        }
        finally {
            IOUtils.closeQuietly(br);
            IOUtils.closeQuietly(in);
        }
        return null;
    }
}
