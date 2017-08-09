package com.cysion.sample;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.cysion.adjointlib.utils.ALog;
import com.cysion.sample.adapter.GlobalAdapter;
import com.cysion.sample.model.BaseData;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private GlobalAdapter mAdapter;
    private Rect mR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ALog.single().init(this);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,1,LinearLayoutManager.VERTICAL, false));
        List<BaseData> imgList = Provider.single().getImgs();
        List<BaseData> spanlist = Provider.single().getSpans();
        imgList.addAll(spanlist);

        mAdapter = new GlobalAdapter(this, imgList);
        mR = new Rect();
        mRecyclerView.post(new Runnable() {
            @Override
            public void run() {
                mRecyclerView.getGlobalVisibleRect(mR);
                mAdapter.setParentLocation(mR);
            }
        });

        mRecyclerView.setAdapter(mAdapter);
    }
}
