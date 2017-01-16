package com.test.demo.myapp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;
import com.test.demo.myapp.R;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class PicassoTestingActvity extends AppCompatActivity {

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picasso_testing_actvity);
        imageView = (ImageView) findViewById(R.id.imageView);
        Picasso.with(this)
                .load("https://appear.in/images/favicon.png")
                .into(imageView);
    }

    public void testPicassoIntercept(View target) {
        final String bearer = "Bearer ";
        okhttp3.OkHttpClient okHttpClient = new okhttp3.OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Log.d("IMAGES", "Intercepting image request");
                Request newRequest = chain.request().newBuilder()
                        .addHeader("Content-Type", "application/json")
                        .addHeader("Authorization", bearer)
                        .build();
                return chain.proceed(newRequest);
            }
        }).build();

        OkHttp3Downloader okHttp3Downloader = new OkHttp3Downloader(okHttpClient);
        Picasso picasso = new Picasso.Builder(this.getApplicationContext()).downloader(okHttp3Downloader).build();
        picasso.setLoggingEnabled(true);

        picasso.load("").into(imageView);

    }

    public void testAlternative(View target){
        okhttp3.OkHttpClient okHttp3Client = new okhttp3.OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Log.d("IMAGES", "Intercept response: " + chain.request());
                        Log.d("IMAGES", "Intercept: challenges: " + chain.request().headers());
                        String bearer = "Bearer ";
                        Request newRequest = chain.request().newBuilder()
                                .addHeader("Content-Type", "application/json")
                                .addHeader("Authorization", bearer)
                                .build();
                        return chain.proceed(newRequest);
                    }
                }).build();
        OkHttp3Downloader okHttp3Downloader = new OkHttp3Downloader(okHttp3Client);
        String url = "";
        Picasso newPicasso = new Picasso.Builder(getApplicationContext()).downloader(okHttp3Downloader).build();
        newPicasso.load(url).into(imageView);
    }

    public void originalCode(View target){
        final String token = "";
        OkHttpClient picassoClient =  new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Log.d("IMAGES", "Intercepting image request");
                Request newRequest = chain.request().newBuilder()
                        .addHeader("Authorization","Bearer " + token)
                        .addHeader("Content-Type", "application/json")
                        .build();
                return chain.proceed(newRequest);
            }
        }).build();
        Picasso picasso = new Picasso.Builder(this.getApplicationContext()).downloader(new OkHttp3Downloader(picassoClient)).build();
        picasso.setLoggingEnabled(true);
        Picasso.setSingletonInstance(picasso);
        Picasso.with(getApplicationContext()).load("").into(imageView);
    }

}
