package com.test.demo.myapp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.test.demo.myapp.R;

public class ViewAsListActivity extends AppCompatActivity {

    private ViewGroup rootLayout;

    private static int BREAKS_THE_APP = 17010;
    private static int PAIN_LEVEL = 16000;
    private static int PRETTY_SMOOTH = 10000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_as_list);
        rootLayout = (ViewGroup) findViewById(R.id.root_linear_layout);
        for (int i = 0; i < BREAKS_THE_APP; i++) {
            addElement();
        }
    }

    private void addElement() {
        View view = getLayoutInflater().inflate(R.layout.my_text_view, rootLayout, false);
        ImageView thumbnail = (ImageView) view.findViewById(R.id.image_content);
        TextView textContent = (TextView) view.findViewById(R.id.text_content);
        textContent.setText("This is text");
        Picasso.with(getApplicationContext()).load("http://eskipaper.com/images/large-2.jpg").into(thumbnail);
        rootLayout.addView(view);
    }
}
