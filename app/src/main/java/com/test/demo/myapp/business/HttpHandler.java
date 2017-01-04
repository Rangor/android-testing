package com.test.demo.myapp.business;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by marsyv on 18.08.2016.
 */
public class HttpHandler {
    public Bitmap getBitmapFromUrl(String stringUrl) throws IOException {
        URL url = new URL(stringUrl);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

        try {
            InputStream in = new BufferedInputStream(httpURLConnection.getInputStream());

            Bitmap bitmap = BitmapFactory.decodeStream(in);
            return bitmap;
        } finally {
            httpURLConnection.disconnect();
        }
    }

    public Bitmap getImageFromUrlWithAuthentication(String token, String stringUrl) throws IOException {
        URL url = new URL(stringUrl);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestProperty("Authorization", "Bearer " + token);
        httpURLConnection.setRequestProperty("Content-Type", "application/json");
        try {
            InputStream in = new BufferedInputStream(httpURLConnection.getInputStream());
            Bitmap bitmap = BitmapFactory.decodeStream(in);
            return bitmap;
        } finally {
            httpURLConnection.disconnect();
        }
    }
}
