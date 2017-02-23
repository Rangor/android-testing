package com.test.demo.myapp.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.test.demo.myapp.R;

public class IntentFilterTestingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_filter_testing);
    }

    public void deeplinkOneClick(View target){
        deepLinkUriLaunch();
    }

    public void deeplinkTwoClick(View target){
        otherDeepLinkuriLaunch();
    }

    private void deepLinkUriLaunch(){
        Uri uri = Uri.parse("example://gizmos");
        Intent deeplink = new Intent(Intent.ACTION_VIEW);
        deeplink.setData(uri);
        startActivity(deeplink);
    }

    private void otherDeepLinkuriLaunch(){
        Uri uri = Uri.parse("example://home");
        Intent deeplink = new Intent(Intent.ACTION_VIEW);
        deeplink.setData(uri);
        startActivity(deeplink);
    }
}
