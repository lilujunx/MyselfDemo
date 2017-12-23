package com.myself.library.base;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.myself.library.R;
import com.myself.library.R2;
import com.myself.library.adapter.EasyRecyclerViewAdapter;
import com.myself.library.utils.TDevice;
import com.myself.library.view.LoadingLayout;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by Malik J on 2017/12/7.
 */

public abstract class BaseRefreshActivity extends BaseActivity implements OnRefreshListener, OnLoadmoreListener {
    @BindView(R2.id.rcy)
    public RecyclerView mRcy;
    @BindView(R2.id.smart)
    public SmartRefreshLayout mSmart;
    @BindView(R2.id.loading)
    public LoadingLayout mLoading;
    @BindView(R2.id.refresh_head)
    public ClassicsHeader mRefreshHead;
    @BindView(R2.id.refresh_footer)
    public ClassicsFooter mRefreshFooter;
    public EasyRecyclerViewAdapter mEasyRecyclerViewAdapter;

    /**
     * 页数，默认为1，下拉刷新中已重置为1
     */
    public int page = 1;

    /**
     * 每页条数，默认为20，可自由更改
     */
    public int limit = 20;
    //    orientation
    private int orientation = 1;
    public static final int orientation_vertical = 1;  //纵向
    public static final int orientation_horizontal = 2;  //横向
    public static final int orientation_random = 3;   //瀑布流
    private SweetAlertDialog mSweetAlertDialog;



    @Override
    public int initRootLayout() {
        return R.layout.layout_refresh;
    }


    /**
     * 默认为纵向1列
     * 参数1为： orientation_vertical   /   orientation_horizontal   /   orientation_random
     * orientation_horizontal  时候  带参数2   横向 +  参数2  列
     * orientation_random      时，带参数2，参数3为
     *
     * @param mOrientation
     */
    public void setOrientation(int... mOrientation) {
        orientation = mOrientation[0];
        int spanCount;
        switch (orientation) {
            case orientation_vertical:
                mRcy.setLayoutManager(new LinearLayoutManager(mActivitySelf));
                break;
            case orientation_horizontal:
                if (mOrientation.length == 2) {
                    spanCount = mOrientation[1];
                    mRcy.setLayoutManager(new GridLayoutManager(mActivitySelf, spanCount));
                } else {
                    mRcy.setLayoutManager(new LinearLayoutManager(mActivitySelf));
                }
                break;
            case orientation_random:
                if (mOrientation.length == 3) {
                    spanCount = mOrientation[1];
                    int orientation = mOrientation[2];
                    mRcy.setLayoutManager(new StaggeredGridLayoutManager(spanCount, orientation));
                } else {
                    mRcy.setLayoutManager(new LinearLayoutManager(mActivitySelf));
                }
                break;
            default:
                mRcy.setLayoutManager(new LinearLayoutManager(mActivitySelf));
                break;
        }
    }


    /**
     * 是否需要下拉头
     *
     * @return
     */
    public boolean needHead() {
        return true;
    }

    ;

    /**
     * 是否需要上拉脚
     *
     * @return
     */
    public boolean needFooter() {
        return true;
    }

    ;

    /**
     * 初始化Adapter
     *
     * @return EasyRecyclerViewAdapter  的实现类
     */
    public abstract EasyRecyclerViewAdapter initAdapter();

    /**
     * 下拉刷新
     */
    public abstract void doRefresh(RefreshLayout refreshlayout, EasyRecyclerViewAdapter mEasyRecyclerViewAdapter);

    /**
     * 上推加载
     */
    public abstract void doLoadMore(RefreshLayout refreshlayout, EasyRecyclerViewAdapter mEasyRecyclerViewAdapter);


    @Override
    public void initViews() {
        mRcy.setLayoutManager(new LinearLayoutManager(mActivitySelf));
        mSmart.setEnableLoadmore(false);
        mSmart.setEnableRefresh(false);
        if (needHead()) {
            mRefreshHead.setVisibility(View.VISIBLE);
            mSmart.setOnRefreshListener(this);
            mSmart.setEnableRefresh(true);
        }
        if (needFooter()) {
            mRefreshFooter.setVisibility(View.VISIBLE);
            mSmart.setEnableLoadmore(true);
            mSmart.setOnLoadmoreListener(this);
        }
        if (mEasyRecyclerViewAdapter == null) {
            mEasyRecyclerViewAdapter = initAdapter();
        }
        mLoading.showLoading();

    }


    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        page = 1;

        if (!TDevice.hasInternet()) {
            refreshlayout.finishRefresh();
            mLoading.showError();
        } else {
            doRefresh(refreshlayout, mEasyRecyclerViewAdapter);
            mRcy.setAdapter(mEasyRecyclerViewAdapter);
            mEasyRecyclerViewAdapter.notifyDataSetChanged();
        }
        mLoading.loadingFinish();
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        page = page + 1;

        if (!TDevice.hasInternet()) {
            refreshlayout.finishLoadmore();
            mLoading.showError();
        } else {
            doLoadMore(refreshlayout, mEasyRecyclerViewAdapter);
            mEasyRecyclerViewAdapter.notifyDataSetChanged();
        }
        mLoading.loadingFinish();
    }


}
