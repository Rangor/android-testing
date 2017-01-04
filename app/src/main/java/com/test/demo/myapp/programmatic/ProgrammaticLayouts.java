package com.test.demo.myapp.programmatic;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.test.demo.myapp.R;

public class ProgrammaticLayouts extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_programmatic_layouts);

        final ViewGroup linearLayout = (ViewGroup) findViewById(R.id.programmatic_root_layout);

        Button bt = new Button(this);
        bt.setText("A Button");
        bt.setLayoutParams(new ActionBar.LayoutParams(ActionBar.LayoutParams.FILL_PARENT,
                ActionBar.LayoutParams.WRAP_CONTENT));
        linearLayout.addView(bt);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button bt = new Button(ProgrammaticLayouts.this);
                bt.setText("A Button");
                bt.setLayoutParams(new ActionBar.LayoutParams(ActionBar.LayoutParams.FILL_PARENT,
                        ActionBar.LayoutParams.WRAP_CONTENT));
                linearLayout.addView(bt);
            }
        });
    }
}
