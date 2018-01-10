package com.jiayou.myapplication;

import com.myself.library.base.BaseFragment;
import com.myself.library.base.BaseMainActivity;

public class MainActivity extends BaseMainActivity {
    private String[] strs = new String[]{"首页", "呵呵呵", "呵呵呵呵1", "呵呵呵2", "个人中心"};
    private int num = 4;
    private int[] bgs = new int[]{R.drawable.selector_label_icon1, R.drawable.selector_label_icon2, R.drawable.selector_label_icon3, R.drawable.selector_label_icon4, R.drawable.selector_label_icon5};
    private BlankFragment0 mBlankFragment0 = new BlankFragment0();
    private BlankFragment1 mBlankFragment1 = new BlankFragment1();
    private BlankFragment2 mBlankFragment2 = new BlankFragment2();
    private BlankFragment3 mBlankFragment3 = new BlankFragment3();
    private BlankFragment4 mBlankFragment4 = new BlankFragment4();
    private BaseFragment[] bfs = new BaseFragment[]{mBlankFragment0, mBlankFragment1, mBlankFragment2, mBlankFragment3, mBlankFragment4};


    @Override
    public int initBottomNum() {
        return num;
    }

    @Override
    public int initBottomHeight() {
        return 50;
    }


    @Override
    public int[] initBottomSelector() {
        return bgs;
    }

    @Override
    public String[] initBottomStrs() {
        return strs;
    }

    @Override
    public BaseFragment[] initFragments() {
        return bfs;
    }

    @Override
    public void initDatas() {

    }

    @Override
    public void initOthers() {

    }
}
