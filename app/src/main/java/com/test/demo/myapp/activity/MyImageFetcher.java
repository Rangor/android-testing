package com.test.demo.myapp.activity;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.test.demo.myapp.R;
import com.test.demo.myapp.business.HttpHandler;

import java.io.IOException;

public class MyImageFetcher extends AppCompatActivity {

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_image_fetcher);
        imageView = (ImageView)findViewById(R.id.my_image_fetcher_imageview);

        final HttpHandler httpHandler = new HttpHandler();

        AsyncTask<Void, Void, Bitmap> myAsyncTask = new AsyncTask<Void, Void, Bitmap>() {

            @Override
            protected Bitmap doInBackground(Void... voids) {

                try {
                    Bitmap bitmap = httpHandler.getBitmapFromUrl("http://i.imgur.com/DvpvklR.png");
                    return bitmap;
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onPostExecute(Bitmap bitmap){
                imageView.setImageBitmap(bitmap);
            }
        };

        myAsyncTask.execute();
    }

    public void getImageClick(View target){
        
    }
}
