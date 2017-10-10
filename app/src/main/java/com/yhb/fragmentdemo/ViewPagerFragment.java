package com.yhb.fragmentdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

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
                ListView listView = (ListView) view.findViewById(R.id.listview);
                ListViewAdapter listViewAdapter=new ListViewAdapter(inflater.getContext());
                listView.setAdapter(listViewAdapter);
               // listView.addFooterView(inflater.inflate(R.layout.fragment_title, null));
                break;
        }

        return view;
    }
}
