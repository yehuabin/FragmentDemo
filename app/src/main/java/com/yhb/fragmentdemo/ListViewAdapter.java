package com.yhb.fragmentdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by smk on 2017/10/11.
 */ //http://www.cnblogs.com/r-decade/p/5827841.html
public class ListViewAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<String> list;
    public ListViewAdapter(Context context,List<String> list) {
        inflater=LayoutInflater.from(context);
        this.list=list;

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=inflater.inflate(R.layout.listview_item,parent,false);
        TextView textView= (TextView) view.findViewById(R.id.tv_name);
        textView.setText("item"+position);
        return view;
    }
}
