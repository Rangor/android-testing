package com.test.demo.myapp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.test.demo.myapp.R;

public class PlayStoreReplicaActivity extends AppCompatActivity {


    LinearLayout rootLinearLayout;
    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_store_replica);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initViews();
        addRowOfApps(1,2);
        addRowOfApps(2,1);
        addRowOfApps(3,10);
        addRowOfApps(4,6);
        addRowOfApps(5,1);
        addRowOfApps(6,4);
        addRowOfApps(7,3);
        addRowOfApps(8,2);
        addRowOfApps(9,20);
    }

    void initViews() {
        rootLinearLayout = (LinearLayout) findViewById(R.id.rootLinearLayout);
    }

    void addRowOfApps(int rowId, int numberOfApps) {
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.content_play_store_replica, null, false);
        LinearLayout scrollViewRoot = (LinearLayout) view.findViewById(R.id.scrollViewRoot);

        for (int i = 0; i < numberOfApps; i++) {
            View appView = inflater.inflate(R.layout.app_item, null, false);
            TextView text = (TextView) appView.findViewById(R.id.app_info_text);
            counter++;
            text.setText("App " + counter);
            appView.setTag("Row: " + rowId + " App Number:" + (i+1) + " of " + numberOfApps);
            scrollViewRoot.addView(appView);
            appView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(PlayStoreReplicaActivity.this, "You clicked : " + view.getTag(), Toast.LENGTH_SHORT).show();
                }
            });
        }
        View header = inflater.inflate(R.layout.text_item, null, false);
        rootLinearLayout.addView(header);
        rootLinearLayout.addView(view);
    }
}
