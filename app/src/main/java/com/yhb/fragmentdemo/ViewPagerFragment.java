package com.yhb.fragmentdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class ViewPagerFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Bundle args = getArguments();
        int position = args.getInt("position");
        View view;
        TextView textView;
        switch (position) {
            case 0:
                view = inflater.inflate(R.layout.fragment_item, container, false);
                textView = (TextView) view.findViewById(R.id.textview);

                textView.setText("Item:" + position);
                break;
            default:
                view = inflater.inflate(R.layout.fragment_item2, container, false);
                final List<String> list=new ArrayList<String>() {};
                for (int i=0;i<20;i++){
                    list.add(i+"");
                }
                ListView listView = (ListView) view.findViewById(R.id.listview);
                final ListViewAdapter listViewAdapter=new ListViewAdapter(getActivity().getBaseContext(),list);
                listView.setAdapter(listViewAdapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Log.d(TAG, "onItemClick: "+position);
                    }
                });
                final SwipeRefreshLayout swipeRefreshLayout= (SwipeRefreshLayout) view.findViewById(R.id.swipe);

                swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary, R.color.colorPrimaryDark);
                swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                       new   android.os.Handler().postDelayed(new Runnable() {
                           @Override
                           public void run() {
                               list.add(0,"t");
                               listViewAdapter.notifyDataSetChanged();
                            swipeRefreshLayout.setRefreshing(false);
                               Log.d(TAG, "run: ");
                           }
                       },1200);
                    }
                });
               // listView.addFooterView(inflater.inflate(R.layout.fragment_title, null));
                break;
        }

        return view;
    }
}
