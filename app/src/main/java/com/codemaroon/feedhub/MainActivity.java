package com.codemaroon.feedhub;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.codemaroon.feedhub.Fragments.AddNewFeedDialogFragment;
import com.codemaroon.feedhub.Fragments.ChatFragment;
import com.codemaroon.feedhub.Fragments.FeedFragment;
import com.codemaroon.feedhub.libs.SlidingTabLayout;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;


public class MainActivity extends AppCompatActivity {

    private Toolbar mToolBar;
    private ViewPager mViewPager;
    private SlidingTabLayout mTabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);

        SharedPreferences feedMeUser;
        feedMeUser = getApplicationContext().getSharedPreferences("feedMeUser", getApplicationContext().MODE_PRIVATE); //1
        //String userId = feedMeUser.getString("userId", "0"); //2
        String username = feedMeUser.getString("username", "null"); //2
        //Toast.makeText(getApplicationContext(), username, Toast.LENGTH_LONG).show();

        //mToolBar = (Toolbar) findViewById(R.id.appBar);
        //setSupportActionBar(mToolBar);
        //getSupportActionBar().setDisplayShowHomeEnabled(true);

        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mTabs = (SlidingTabLayout) findViewById(R.id.tabs);
        mTabs.setDistributeEvenly(true);
        mViewPager.setAdapter(new SwipePagerAdapter(getSupportFragmentManager()));
        mTabs.setViewPager(mViewPager);

        Resources resources = getApplicationContext().getResources();
        ImageView icon = new ImageView(this);
        icon.setImageDrawable(resources.getDrawable(R.mipmap.androidplus));

        FloatingActionButton actionButton = new FloatingActionButton.Builder(this)
                .setContentView(icon)
                //.setPosition(FloatingActionButton.)
                .build();

        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddNewFeedDialogFragment addNewFeed = new AddNewFeedDialogFragment();
                addNewFeed.show(getFragmentManager(), "addNewDialog");
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_logout) {
            Toast.makeText(getApplicationContext(), "sdfs", Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

class SwipePagerAdapter extends FragmentPagerAdapter {

    private String[] tabTitles = {"Feed", "Chat"};

    public SwipePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        Fragment returnedFragment = null;
        if (i == 0) {
            returnedFragment = new FeedFragment();
        }
        if (i == 1) {
            returnedFragment = new ChatFragment();
        }
        return returnedFragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}