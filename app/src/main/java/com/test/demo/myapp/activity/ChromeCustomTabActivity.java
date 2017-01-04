package com.test.demo.myapp.activity;

import android.content.ComponentName;
import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsCallback;
import android.support.customtabs.CustomTabsClient;
import android.support.customtabs.CustomTabsIntent;
import android.support.customtabs.CustomTabsServiceConnection;
import android.support.customtabs.CustomTabsSession;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.test.demo.myapp.R;

public class ChromeCustomTabActivity extends AppCompatActivity {
    public static final String TAG = "CustomChromeTab";
    private static final String URL = "https://fmdm.smartphones.no/";
    static String Stable = "com.android.chrome";
    public static final String CUSTOM_TAB_PACKAGE_NAME = Stable;
    static String Beta = "com.chrome.beta";
    static String Dev = "com.chrome.dev";
    CustomTabsClient mCustomTabsClient;
    CustomTabsSession customTabsSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chrome_custom_tab);


        CustomTabsServiceConnection connection = new CustomTabsServiceConnection() {
            @Override
            public void onCustomTabsServiceConnected(ComponentName name, CustomTabsClient client) {
                Log.d(TAG, "onCustomTabsServiceConnected");
                mCustomTabsClient = client;
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.d(TAG, "onServiceDisconnected");
            }
        };

        CustomTabsClient.bindCustomTabsService(this, CUSTOM_TAB_PACKAGE_NAME, connection);

        if (mCustomTabsClient == null) {
            Toast.makeText(this, "Client was null", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Client was not null", Toast.LENGTH_SHORT).show();
        }


    }


//        customTabsIntent.intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

    public CustomTabsSession newSession() {

        return mCustomTabsClient.newSession(new CustomTabsCallback() {

            @Override
            public void onNavigationEvent(int navigationEvent, Bundle extras) {
                Log.d(TAG, "onNavigationEvent: Code = " + navigationEvent);
                switch (navigationEvent) {
                    case NAVIGATION_STARTED:
                        Log.d(TAG, "Navigation started");
                        break;
                    case NAVIGATION_FINISHED:
                        Log.d(TAG, "Navigation finished");
                        break;
                    case NAVIGATION_FAILED:
                        Log.d(TAG, "Navigation failed");
                        break;
                    case NAVIGATION_ABORTED:
                        Log.d(TAG, "Navigation aborted");
                        break;
                    case TAB_HIDDEN:
                        Log.d(TAG, "Tab hidden");
                        break;
                    case TAB_SHOWN:
                        Log.d(TAG, "Tab shown");
                        break;
                }
            }
        });
    }

    public void checkStatusClick(View target){
        String defaultPackage = CustomTabsClient.getPackageName(ChromeCustomTabActivity.this, null);
        Toast.makeText(this, defaultPackage, Toast.LENGTH_SHORT).show();
        if (mCustomTabsClient == null) {
            Toast.makeText(this, "Client was null", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Client was not null, trying to prefetch", Toast.LENGTH_SHORT).show();
            mCustomTabsClient.warmup(0);
            customTabsSession = newSession();
            boolean mayLaunch = customTabsSession.mayLaunchUrl(Uri.parse(URL), null, null);
            if(mayLaunch){
                Toast.makeText(this, "Maylaunch is true against " + URL, Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void launchUrlClick(View target) {
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder(customTabsSession);
        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(this, Uri.parse(URL));
    }
}
