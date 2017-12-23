package com.myself.library.base;

import android.support.v7.widget.DefaultItemAnimator;
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

/**
 * Created by Malik J on 2017/12/7.
 */

public abstract class BaseRefreshFragment extends BaseFragment implements OnRefreshListener, OnLoadmoreListener {
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
    public int page = 1;
    public int limit = 10;
    //    orientation
    private int orientation = 1;
    public static final int orientation_vertical = 1;  //纵向
    public static final int orientation_horizontal = 2;  //横向
    public static final int orientation_random = 3;   //瀑布流

    @Override
    public int initRootLayout() {
        return R.layout.layout_refresh;
    }



    /**
     * orientation_vertical    //纵向         1参
     * orientation_horizontal   //横向     +   列数
     * orientation_random    //瀑布流      +   列数     + 方向（StaggeredGridLayoutManager.HORIZONTAL/StaggeredGridLayoutManager.vertical）
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
    public abstract boolean needHead();

    /**
     * 是否需要上拉脚
     *
     * @return
     */
    public abstract boolean needFooter();

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
        mRcy.setItemAnimator(new DefaultItemAnimator());
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


    }





    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        page = 1;

        if (!TDevice.hasInternet()) {
            refreshlayout.finishRefresh();
            mLoading.loadingFinish();
            mLoading.showError();

        } else {
            doRefresh(refreshlayout, mEasyRecyclerViewAdapter);
            mRcy.setAdapter(mEasyRecyclerViewAdapter);
            mEasyRecyclerViewAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        page = page + 1;

        if (!TDevice.hasInternet()) {
            refreshlayout.finishLoadmore();
            mLoading.loadingFinish();
            mLoading.showError();
        } else {
            doLoadMore(refreshlayout, mEasyRecyclerViewAdapter);
            mEasyRecyclerViewAdapter.notifyDataSetChanged();
        }
    }



}
