package com.test.demo.myapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;
import com.test.demo.myapp.R;

public class GlideTesterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide_tester);

        ImageView imageView = (ImageView) findViewById(R.id.imageView2);
        ImageView imageView2 = (ImageView) findViewById(R.id.imageView3);

//        String imageUrl = "http://eskipaper.com/images/large-2.jpg";
        String imageUrl = "http://api.met.no/weatherapi/weathericon/1.1/?symbol=3;content_type=image/png";

        Glide.with(this).load(imageUrl).into(imageView);
        Picasso.with(this).load(imageUrl).into(imageView2);

    }
}
