package com.test.demo.myapp.activity;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.test.demo.myapp.R;

import java.util.ArrayList;
import java.util.List;

public class MemoryTesterActivity extends AppCompatActivity {

    List<String> memoryHog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_tester);

        memoryHog = new ArrayList<>();
        for(double i = 0; i < 1000000; i++){
            String number = Double.toString(i);
            memoryHog.add(number);
        }

    }

    public void asynctaskexecute(View target){
        new MyAsyncTask().execute();
    }

    private class MyAsyncTask extends AsyncTask {
        @Override
        protected Object doInBackground(Object[] params) {
            List<String> list = new ArrayList<>();
            for(double i = 0; i < 1000000; i++){
                String number = Double.toString(i);
                list.add(number);
            }
            return doSomeStuff();
        }
        private Object doSomeStuff() {
            //do something to get result
            return new Object();
        }
    }
}
