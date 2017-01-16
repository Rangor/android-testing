package com.test.demo.myapp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.test.demo.myapp.R;

public class RecyclerTestActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_test);
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        ListObject[] listObjects = new ListObject[30000];
        for(int i = 0; i < listObjects.length; i++){
            ListObject listObject = new ListObject();
            listObject.setText("Hello");
            listObject.setType(ListObject.Type.ITEM);
            listObject.setUrl("http://eskipaper.com/images/large-2.jpg");
            listObjects[i] = listObject;
        }
        adapter = new MyAdapter(listObjects, getApplicationContext());
        recyclerView.setAdapter(adapter);
    }
}
