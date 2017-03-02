package com.test.demo.myapp.activity;

import android.os.Bundle;
import android.provider.Settings;
import android.provider.Settings.Secure;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.test.demo.myapp.R;

public class ConstraintLayoutTesting extends AppCompatActivity {


    private String uniqueString;

    private TextView uniqueStringTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constraint_layout_testing);
        uniqueStringTextView = (TextView) findViewById(R.id.uniqueStringTextView);
    }

    private void generateUniqueId(){
        uniqueString = Settings.Secure.getString(getApplicationContext().getContentResolver(), Secure.ANDROID_ID);
        uniqueStringTextView.setText(uniqueString);
    }

    public void showUniqueIdButtonClick(View target){
        generateUniqueId();
        Log.d("ConstraintLayoutTesting", uniqueString);
    }
}
