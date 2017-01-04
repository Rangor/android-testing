package com.test.demo.myapp.business;


import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpHelper {

    public static String performHttpPost(String stringUrl) throws IOException {
        URL url = new URL(stringUrl);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setDoInput(true);
        try {
            if(HttpURLConnection.HTTP_OK != httpURLConnection.getResponseCode()){
                return null;
            }
            InputStream in = httpURLConnection.getInputStream();
            return InputStreamHelper.inputStreamToString(in);
        }finally {
            httpURLConnection.disconnect();
        }
    }

    public static String performHttpGet(String stringUrl) throws IOException {
        URL url = new URL(stringUrl);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = httpURLConnection.getInputStream();
            return InputStreamHelper.inputStreamToString(in);
        }finally {
            httpURLConnection.disconnect();
        }
    }

    public static Object[] getObjectArray(String authentication, String stringUrl) throws IOException {
        URL url = new URL(stringUrl);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestProperty("authorization", authentication);
        try {
            InputStream in = httpURLConnection.getInputStream();
            Gson gson = new Gson();
            Reader reader = new InputStreamReader(in);
            return gson.fromJson(reader, Object[].class);
        }finally {
            httpURLConnection.disconnect();
        }
    }

    public static Object getObject(String stringUrl) throws IOException {
        URL url = new URL(stringUrl);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = httpURLConnection.getInputStream();
            Gson gson = new Gson();
            Reader reader = new InputStreamReader(in);
            return gson.fromJson(reader, Object.class);
        }finally {
            httpURLConnection.disconnect();
        }
    }

}
