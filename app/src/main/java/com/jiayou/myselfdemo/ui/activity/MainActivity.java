package com.jiayou.myselfdemo.ui.activity;

import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.jiayou.myselfdemo.R;

import com.jiayou.myselfdemo.base.LoadingActivity;
import com.myself.library.utils.TDevice;

public class MainActivity extends LoadingActivity {


    @Override
    public int initRootLayout() {
        return R.layout.main;
    }


    @Override
    public boolean isUseTitleBar() {
        return true;
    }

    @Override
    public void initDatas() {

    }


    @Override
    public void initView() {
        setTitleCenter("dsadawdwa");
    }

    @Override
    public void onReload() {
        Toast.makeText(mActivitySelf, "xxxxx", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                onReloadSucess();
            }
        },2000);
    }


    @Override
    public void initOthers() {

    }

    public void doNet(View mView) {
        noIntenet(TDevice.hasInternet());
    }
}
