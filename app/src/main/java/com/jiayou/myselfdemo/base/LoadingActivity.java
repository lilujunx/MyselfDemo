package com.jiayou.myselfdemo.base;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.jiayou.myselfdemo.R;

/**
 * Created by Malik J on 2017/12/23.
 */

public abstract class LoadingActivity extends GBaseActivity {


    private View test;
    private View root;

    @Override
    public void initViews() {
        FrameLayout mFrameLayout = new FrameLayout(mActivitySelf);
        ViewGroup.LayoutParams mLayoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mFrameLayout.setLayoutParams(mLayoutParams);
        int mTest = R.layout.test;
        test = LayoutInflater.from(mActivitySelf).inflate(mTest, null);
        test.setVisibility(View.GONE);
        mFrameLayout.addView(test);
        int mI = initRootLayout();
        root = LayoutInflater.from(mActivitySelf).inflate(mI, null);
        mFrameLayout.addView(root);
        if (isUseTitleBar()) {
            addTitleBar(mFrameLayout);
        } else {
            setContentView(mFrameLayout);
        }
        initView();
    }

    public abstract void initView();


    public void noIntenet(boolean hasIntenet) {
        if (hasIntenet) {
            test.setVisibility(View.GONE);
            root.setVisibility(View.VISIBLE);
        } else {
            test.setVisibility(View.VISIBLE);
            test.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onReload();
                }
            });
            root.setVisibility(View.GONE);
        }
    }


    public abstract void onReload();

    public void onReloadSucess() {
        test.setVisibility(View.GONE);
        root.setVisibility(View.VISIBLE);
    }
}
