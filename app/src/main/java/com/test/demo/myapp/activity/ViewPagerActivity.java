package com.test.demo.myapp.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.test.demo.myapp.R;
import com.test.demo.myapp.fragments.ContentFragment;

public class ViewPagerActivity extends AppCompatActivity implements ContentFragment.OnFragmentInteractionListener {

    private static final int NUM_PAGES = 3;

    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void finishWizardClick(View target){
        Toast.makeText(getApplicationContext(), "You tried to finish the Wizard, the wonderfull Wizard of Oz", Toast.LENGTH_SHORT).show();
    }

    /**
     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
     * sequence.
     */
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            int layout = 0;

            if(position == 0){
                layout = R.layout.wizard_1;
            }

            if(position == 1){
                layout = R.layout.wizard_2;
            }

            if(position == 2){
                layout = R.layout.wizard_3;
            }

            Fragment fragment = ContentFragment.newInstance(layout);
            return fragment;
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}
