package com.yhb.fragmentdemo;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        tabLayout= (TabLayout) findViewById(R.id.tablayout);

        viewPager= (ViewPager) findViewById(R.id.viewpager);
        ViewPagerAdpter adpter=new ViewPagerAdpter(getSupportFragmentManager());
        viewPager.setAdapter(adpter);

        tabLayout.setupWithViewPager(viewPager);
    }
}

class ViewPagerAdpter extends FragmentPagerAdapter{

    public ViewPagerAdpter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "Item"+position;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment=new ViewPagerFragment();
        Bundle bundle=new Bundle();
        bundle.putInt("position",position);
        fragment.setArguments(bundle);
        return fragment;
    }
}

