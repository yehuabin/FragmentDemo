package com.yhb.recyclerviewdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by Administrator on 2017/10/12.
 */
public class FootViewAdapter extends RecyclerView.Adapter {
    private LayoutInflater inflater;
    private List<String> mTitles;

    public FootViewAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        this.mTitles = new ArrayList<String>();
        for (int i = 0; i < 20; i++) {
            int index = i + 1;
            mTitles.add("item" + index);

        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: " + viewType);
        View view ;
        RecyclerView.ViewHolder viewHolder;
        if (viewType == 1) {
            view = inflater.inflate(R.layout.footview, parent, false);
            viewHolder = new FootViewHolder(view);
        } else {
            view = inflater.inflate(R.layout.recycler_item, parent, false);
            viewHolder = new ItemViewHolder(view);
        }

        return viewHolder;
    }

    @Override
    public int getItemViewType(int position) {
        if (position >= mTitles.size()) {
            return 1;
        }
        return 0;
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
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
            itemViewHolder.textView.setText(mTitles.get(position));
        } else if (holder instanceof FootViewHolder) {
            FootViewHolder footViewHolder = (FootViewHolder) holder;
            footViewHolder.textView.setText("loading");
        }
    }

    @Override
    public int getItemCount() {
        return mTitles.size() + 1;
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;

        public ItemViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.textview);
        }
    }

    public static class FootViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;

        public FootViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.textview);
        }
    }
}
