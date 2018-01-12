package com.jiayou.myselfdemo.ui.activity;

import android.view.View;
import android.widget.RelativeLayout;

import com.jiayou.myselfdemo.R;
import com.jiayou.myselfdemo.base.GBaseActivity;
import com.jiayou.myselfdemo.ui.listener.NetService;
import com.jiayou.myselfdemo.utils.NetUtil;
import com.jiayou.myselfdemo.utils.TLog;
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


    public void go1(View v){
        NetService mInstance = NetUtil.getInstance();
        TLog.error(mInstance.toString());
    }

    public void go2(View v){
        NetService mInstance = NetUtil.getLongConnectionInstance();
        TLog.error(mInstance.toString());
    }

    public void go3(View v){
        NetService mInstance = NetUtil.getInstance();
        TLog.error(mInstance.toString());
    }

    public void go4(View v){

    }
}
