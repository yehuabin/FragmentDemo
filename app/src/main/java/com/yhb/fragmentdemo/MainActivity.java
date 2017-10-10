package com.yhb.fragmentdemo;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.HashMap;

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
        ViewPagerAdapter adpter=new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adpter);

        tabLayout.setupWithViewPager(viewPager);
    }
}

class ViewPagerAdapter extends FragmentPagerAdapter{

    public ViewPagerAdapter(FragmentManager fm) {
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
//http://www.cnblogs.com/r-decade/p/5827841.html
class ListViewAdapter extends BaseAdapter{
    private LayoutInflater inflater;
    private HashMap<Object,String> hashMap;
    public ListViewAdapter(Context context) {
        inflater=LayoutInflater.from(context);
        hashMap=new HashMap<>();
        hashMap.put(0,"Item00");
        hashMap.put(1,"Item1");
        hashMap.put(2,"Item2");
    }

    @Override
    public int getCount() {
        return hashMap.size();
    }

    @Override
    public Object getItem(int position) {
        return hashMap.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=inflater.inflate(R.layout.listview_item,null);
        TextView textView= (TextView) view.findViewById(R.id.tv_name);
        textView.setText(hashMap.get(position));
        return view;
    }
}

