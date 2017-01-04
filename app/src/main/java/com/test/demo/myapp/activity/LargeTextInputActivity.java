package com.test.demo.myapp.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.test.demo.myapp.R;

public class LargeTextInputActivity extends AppCompatActivity {

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_large_text_input);
        editText = (EditText) findViewById(R.id.largeEditText);
    }


    public void confirmClick(View target) {
        Intent result = new Intent();
        result.putExtra("text", editText.getText().toString());
        setResult(DialogTestingActivity.REQUEST_LARGE_TEXT, result);
        finish();
    }

}
