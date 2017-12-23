package com.jiayou.myselfdemo.ui.activity;

import android.os.Handler;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.jiayou.myselfdemo.R;
import com.jiayou.myselfdemo.ui.adapter.DemoAdapter;
import com.myself.library.adapter.EasyRecyclerViewAdapter;
import com.myself.library.base.BaseRefreshActivity;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity4 extends BaseRefreshActivity {
    private List<String> mEntity = new ArrayList<>();
    private DemoAdapter mDemoAdapter;


    @Override
    public boolean needFooter() {
        return true;
    }


    @Override
    public EasyRecyclerViewAdapter initAdapter() {
        mDemoAdapter = new DemoAdapter();
        return mDemoAdapter;
    }


    @Override
    public boolean isUseTitleBar() {
        return true;
    }

    @Override
    public void doRefresh(RefreshLayout refreshlayout, EasyRecyclerViewAdapter mEasyRecyclerViewAdapter) {

        mEntity.clear();
        initDatas();
        mLoading.showContent();
        mEasyRecyclerViewAdapter.notifyDataSetChanged();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mSmart.finishRefresh();

                Toast.makeText(MainActivity4.this, "15000", Toast.LENGTH_SHORT).show();
            }
        }, 1500);
    }

    @Override
    public void doLoadMore(RefreshLayout refreshlayout, EasyRecyclerViewAdapter mEasyRecyclerViewAdapter) {

    }


    @Override
    public void initDatas() {
        for (int i = 0; i < 70; i++) {
            mEntity.add("xx1");
        }
        mDemoAdapter.setList(mEntity);
        mLoading.loadingFinish();
        mLoading.setRetryListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mActivitySelf, "再来一次", Toast.LENGTH_SHORT).show();
                mLoading.showLoading();
                mSmart.autoRefresh();
            }
        });
        setOrientation(orientation_random, 3, StaggeredGridLayoutManager.VERTICAL);
        mDemoAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = (int) v.getTag();
                mEntity.remove(pos);
                mDemoAdapter.notifyItemRemoved(pos);
                mSmart.autoRefresh();
            }
        });
        mRcy.setAdapter(mDemoAdapter);
    }

    @Override
    public void initOthers() {
        setTitleCenter("哈哈哈哈164894");
        setTitleLeft(R.mipmap.ic_launcher, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mActivitySelf, "left", Toast.LENGTH_SHORT).show();
            }
        });
    setTitleRight(R.mipmap.ic_launcher, R.mipmap.ic_del, new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(mActivitySelf, "11111", Toast.LENGTH_SHORT).show();
        }
    }, new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(mActivitySelf, "22222:" + 22222, Toast.LENGTH_SHORT).show();
        }
    });

    }
}
