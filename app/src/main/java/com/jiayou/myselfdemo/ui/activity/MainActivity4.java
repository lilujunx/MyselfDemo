package com.jiayou.myselfdemo.ui.activity;

import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.jiayou.myselfdemo.R;
import com.jiayou.myselfdemo.ui.adapter.DemoAdapter;
import com.myself.library.adapter.EasyRecyclerViewAdapter;
import com.myself.library.base.BaseRefreshActivity;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.util.ArrayList;

public class MainActivity4 extends BaseRefreshActivity {
    private ArrayList<String> mEntity = new ArrayList<>();
    private DemoAdapter mDemoAdapter;


    @Override
    public boolean needFooter() {
        return false;
    }


    @Override
    public EasyRecyclerViewAdapter initAdapter() {
        mDemoAdapter = new DemoAdapter(mActivitySelf);

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
        for (int i = 0; i < 5; i++) {
            mEntity.add("xx" + i);
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
//        setOrientation(orientation_random, 3, StaggeredGridLayoutManager.VERTICAL);
        setOrientation(orientation_vertical);
        mDemoAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = (int) v.getTag();
                Log.e("xx", "我要删除，点到了：" + pos + "个");
                mDemoAdapter.remove(pos);
//                mDemoAdapter.notifyItemChanged(pos);

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
