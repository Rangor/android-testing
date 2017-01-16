package com.test.demo.myapp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.test.demo.myapp.R;

public class ListActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        ListObject[] myDataset = new ListObject[3];
        ListObject headerObject = new ListObject();
        headerObject.setText("HEADER TEXT");
        headerObject.setType(ListObject.Type.HEADER);

        ListObject itemObject = new ListObject();
        itemObject.setText("Even");
        itemObject.setType(ListObject.Type.ITEM);
        myDataset[0] = headerObject;
        myDataset[1] = itemObject;
        myDataset[2] = itemObject;
        mAdapter = new MyAdapter(myDataset, getApplicationContext());
        mRecyclerView.setAdapter(mAdapter);
    }
}
