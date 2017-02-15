package com.test.demo.myapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.test.demo.myapp.activity.CardsActivity;
import com.test.demo.myapp.activity.ChromeCustomTabActivity;
import com.test.demo.myapp.activity.DialogTestingActivity;
import com.test.demo.myapp.activity.GlideTesterActivity;
import com.test.demo.myapp.activity.ImagePickerActivity;
import com.test.demo.myapp.activity.ListActivity;
import com.test.demo.myapp.activity.MemoryTesterActivity;
import com.test.demo.myapp.activity.MyImageFetcher;
import com.test.demo.myapp.activity.NotificationTesterActivity;
import com.test.demo.myapp.activity.PicassoTestingActvity;
import com.test.demo.myapp.activity.PlayStoreReplicaActivity;
import com.test.demo.myapp.activity.PresenterTesterActivity;
import com.test.demo.myapp.activity.PullToRefreshActivity;
import com.test.demo.myapp.activity.RecyclerTestActivity;
import com.test.demo.myapp.activity.ReprintTestActivity;
import com.test.demo.myapp.activity.ScrollingActivity;
import com.test.demo.myapp.activity.TextViewTesterActivity;
import com.test.demo.myapp.activity.ViewAsListActivity;
import com.test.demo.myapp.activity.ViewPagerActivity;
import com.test.demo.myapp.activity.ZxingGenerateBarcodeActvity;
import com.test.demo.myapp.programmatic.ProgrammaticLayouts;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onPullToRefreshClick(View target){
        startActivity(new Intent(this, PullToRefreshActivity.class));
    }

    public void onPicassoClick(View target){
        startActivity(new Intent(this, PicassoTestingActvity.class));
    }

    public void barcodeOnClick(View target){
        startActivity(new Intent(this, ZxingGenerateBarcodeActvity.class));
    }

    public void myImageFetcherClick(View target){
        startActivity(new Intent(this, MyImageFetcher.class));
    }

    public void textViewTestingClick(View target){
        startActivity(new Intent(this, TextViewTesterActivity.class));
    }

    public void fragmentTestButtonClick(View target){
        startActivity(new Intent(this, TextViewTesterActivity.class));
    }

    public void startScrollingActivityClick(View target){
        startActivity(new Intent(this, ScrollingActivity.class));
    }

    public void startListActivityClick(View target){
        startActivity(new Intent(this, ListActivity.class));
    }

    public void startCardsActivity(View target){
        startActivity(new Intent(this, CardsActivity.class));
    }

    public void startProgrammaticActivity(View target){
        startActivity(new Intent(this, ProgrammaticLayouts.class));
    }

    public void startPlayStoreReplicaActivity(View target) {
        startActivity(new Intent(this, PlayStoreReplicaActivity.class));
    }

    public void startChromeCustomTabActivity(View view){
        startActivity(new Intent(this, ChromeCustomTabActivity.class));
    }

    public void startDialogTestingActivity(View view){
        startActivity(new Intent(this, DialogTestingActivity.class));
    }

    public void startImagePickerActivity(View view){
        startActivity(new Intent(this, ImagePickerActivity.class));
    }

    public void startSecureStorageTesting(View target){

    }

    public void startMemoryTesting(View target){
        startActivity(new Intent(this, MemoryTesterActivity.class));
    }

    public void startReprintTesting(View target){
        startActivity(new Intent(this, ReprintTestActivity.class));
    }

    public void startPresenterTester(View target){
        startActivity(new Intent(this, PresenterTesterActivity.class));
    }

    public void startGlideTester(View target){
        startActivity(new Intent(this, GlideTesterActivity.class));
    }

    public void startNotificationTester(View target){
        startActivity(new Intent(this, NotificationTesterActivity.class));
    }

    public void startRecycleViewTester(View target){
        startActivity(new Intent(this, RecyclerTestActivity.class));
    }

    public void startViewListActivity(View target){
        startActivity(new Intent(this, ViewAsListActivity.class));
    }

    public void startViewPagerActivity(View target){
        startActivity(new Intent(this, ViewPagerActivity.class));
    }

    private void deepLinkUriLaunch(){
        Uri uri = Uri.parse("example://gizmos");
        Intent deeplink = new Intent(Intent.ACTION_VIEW);
        deeplink.setData(uri);
        startActivity(deeplink);
    }
}
