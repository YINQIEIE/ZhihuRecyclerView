package com.yq.zhihurecyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rv;
    private MyAdapter adapter;
    private LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv = (RecyclerView) findViewById(R.id.rv);
        rv.setLayoutManager(mLayoutManager = new LinearLayoutManager(this));

        adapter = new MyAdapter(this);
        rv.setAdapter(adapter);

        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                int first = mLayoutManager.findFirstVisibleItemPosition();
                int last = mLayoutManager.findLastCompletelyVisibleItemPosition();
                for (int i = first; i <= last; i++) {
                    View view = mLayoutManager.findViewByPosition(i);
                    AdImageView iv_ad = view.findViewById(R.id.iv_ad);
                    if (iv_ad.getVisibility() == View.VISIBLE) {
                        iv_ad.setdY(mLayoutManager.getHeight() - view.getTop(), mLayoutManager.getHeight());
                        Log.i("imageview", mLayoutManager.getHeight() + ">>>" + view.getTop());
                    }
                }
            }
        });
    }


}
