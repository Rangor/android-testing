package com.test.demo.myapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.test.demo.myapp.R;
import com.test.demo.myapp.presenter.WeakReferencePresenter;

public class PresenterTesterActivity extends AppCompatActivity implements WeakReferencePresenter.WeakReferencePresenterCallback {

    public static final String TAG = "PresenterTesterActivty";
    private WeakReferencePresenter presenter;

    TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presenter_tester);
        resultTextView = (TextView)findViewById(R.id.resultTextView);
        presenter = new WeakReferencePresenter(this);
        presenter.getSomeData();
    }

    @Override
    public void showSomeDataWeak(String data) {
        Toast.makeText(PresenterTesterActivity.this, data, Toast.LENGTH_LONG).show();
        resultTextView.setText(data);
        resultTextView = (TextView)findViewById(R.id.resultTextView);
    }

    @Override
    public void showSomeDataStrong(String data) {
        Toast.makeText(PresenterTesterActivity.this, data, Toast.LENGTH_LONG).show();
        resultTextView.setText(data);
        resultTextView = (TextView)findViewById(R.id.resultTextView);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "OnDestroy");
        Log.d(TAG, resultTextView.toString());
    }
}
