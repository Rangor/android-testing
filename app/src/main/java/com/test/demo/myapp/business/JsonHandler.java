package com.test.demo.myapp.business;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by marsyv on 18.08.2016.
 */
public class JsonHandler {

    public JSONObject httpsGetJsonObject(String stringUrl) throws IOException, JSONException {
        URL url = new URL("http://www.android.com/");
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            JSONObject jsonObject = new JSONObject(InputStreamUtil.inputStreamToString(in));
            return jsonObject;
        } finally {
            urlConnection.disconnect();
        }
    }

}
