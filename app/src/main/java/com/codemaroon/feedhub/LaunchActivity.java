package com.codemaroon.feedhub;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;

import com.codemaroon.feedhub.Fragments.ChatFragment;
import com.codemaroon.feedhub.Fragments.FeedFragment;
import com.codemaroon.feedhub.libs.SlidingTabLayout;

public class LaunchActivity extends AppCompatActivity {

    private Toolbar mToolBar;
    private ViewPager mViewPager;
    private SlidingTabLayout mTabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

//        mToolBar = (Toolbar) findViewById(R.id.appBar);
//        setSupportActionBar(mToolBar);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//
//        mViewPager = (ViewPager) findViewById(R.id.viewPager);
//        mTabs = (SlidingTabLayout) findViewById(R.id.tabs);
//        mTabs.setDistributeEvenly(true);
//        mViewPager.setAdapter(new SwipePagerAdapter(getSupportFragmentManager()));
//        mTabs.setViewPager(mViewPager);
    }
}

//class SwipePagerAdapter extends FragmentPagerAdapter {
//
//    private String[] tabTitles = {"Feed", "Chat"};
//
//    public SwipePagerAdapter(FragmentManager fm) {
//        super(fm);
//    }
//
//    @Override
//    public Fragment getItem(int i) {
//        Fragment returnedFragment = null;
//        if (i == 0) {
//            returnedFragment = new FeedFragment();
//        }
//        if (i == 1) {
//            returnedFragment = new ChatFragment();
//        }
//        return returnedFragment;
//    }
//
//    @Override
//    public int getCount() {
//        return 2;
//    }
//
//    @Override
//    public CharSequence getPageTitle(int position) {
//        return tabTitles[position];
//    }
//}