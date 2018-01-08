package com.jiayou.myselfdemo.ui.activity;

import android.view.View;
import android.widget.RelativeLayout;

import com.jiayou.myselfdemo.R;
import com.jiayou.myselfdemo.base.GBaseActivity;
import com.myself.library.view.love.PeriscopeLayout;

import butterknife.BindView;

public class LikeActivity extends GBaseActivity {


    @BindView(R.id.activity_like)
    PeriscopeLayout mActivityLike;

    @Override
    public int initRootLayout() {
        return R.layout.activity_like;
    }

    @Override
    public void initViews() {


    }

    @Override
    public void initDatas() {

    }

    @Override
    public void initOthers() {

    }

    public void like1(View b) {
        mActivityLike.addHeart();
    }

    public void like2(View b) {
        RelativeLayout.LayoutParams mLayoutParams = new RelativeLayout.LayoutParams(0, 0);
        mLayoutParams.leftMargin =  b.getLeft();
        mLayoutParams.topMargin =  b.getTop();
//        Log.e("xx", "mTvGood.getLeft()：" + mTvGood.getLeft() + ",,,," + "mTvGood.getTop():" + mTvGood.getTop());
        mActivityLike.addHeart(b, mLayoutParams);
//        mActivityLike.addHeart(b);
    }


}
