package com.yhb.recyclerviewdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/10/12.
 */
public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder> {
    private LayoutInflater inflater;
    private List<String> mTitles;

    public RecycleViewAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        this.mTitles = new ArrayList<String>();
        for (int i = 0; i < 20; i++) {
            int index = i + 1;
            mTitles.add("item" + index);;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recycler_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    //添加数据
    public void addItem(List<String> newDatas) {
        //mTitles.add(position, data);
        //notifyItemInserted(position);
        newDatas.addAll(mTitles);
        mTitles.removeAll(mTitles);
        mTitles.addAll(newDatas);
        notifyDataSetChanged();
    }
    public void addMoreItem(List<String> newDatas) {
        mTitles.addAll(newDatas);
        notifyDataSetChanged();
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setText(mTitles.get(position));
    }

    @Override
    public int getItemCount() {
        return mTitles.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.textview);
        }
    }
}
