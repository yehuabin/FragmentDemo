package com.yhb.recyclerviewdemo;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * http://blog.csdn.net/developer_jiangqq/article/details/49927631
 * http://blog.csdn.net/developer_jiangqq/article/details/49992269
 */
public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private RecycleViewAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private int lastVisibleItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new RecycleViewAdapter(this);
        recyclerView.setAdapter(adapter);

        swipeRefreshLayout= (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light,
                android.R.color.holo_red_light,android.R.color.holo_orange_light,
                android.R.color.holo_green_light);
        swipeRefreshLayout.setProgressViewOffset(false, 0, (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
                        .getDisplayMetrics()));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        List<String> newDatas = new ArrayList<String>();
                        for (int i = 0; i <5; i++) {
                            int index = i + 1;
                            newDatas.add("new item" + index);
                        }
                        adapter.addItem(newDatas);
                        swipeRefreshLayout.setRefreshing(false);
                        Toast.makeText(MainActivity.this, "更新了5条数据", Toast.LENGTH_SHORT).show();
                    }
                },3000);
            }
        });

         lastVisibleItem=linearLayoutManager.findLastVisibleItemPosition();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            //用户上拉或下拉时候执行
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);


                if (lastVisibleItem+1==adapter.getItemCount()&&RecyclerView.SCROLL_STATE_IDLE==newState){
                    Toast.makeText(MainActivity.this, "loading", Toast.LENGTH_SHORT).show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            List<String> newDatas = new ArrayList<String>();
                            for (int i = 0; i< 5; i++) {
                                int index = i +1;
                                newDatas.add("more item" + index);
                            }
                            adapter.addMoreItem(newDatas);
                        }
                    },1000);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem=linearLayoutManager.findLastVisibleItemPosition();
            }
        });
    }


}

