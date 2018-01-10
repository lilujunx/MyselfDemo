package com.myself.library.base;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.myself.library.R;
import com.myself.library.utils.TDevice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Malik J on 2017/12/18.
 */

public abstract class BaseMainActivity extends BaseActivity implements View.OnClickListener {


    private RadioGroup mRadioGroup;
    private int oriColor = R.color.text_main, selectColor = R.color.app_style;
    private List<BaseFragment> mAll;
    private List<RadioButton> mRbs = new ArrayList<>();
    public BaseFragment mShowFragment;
    private BaseFragment[] mBaseFragments;

    /**
     * @return 有几个导航按钮
     */
    public abstract int initBottomNum();


    /**
     * 设置底部点击的颜色
     *
     * @param mOriColor
     * @param mSelectColor
     */
    public void setOriColor(int mOriColor, int mSelectColor) {
        oriColor = mOriColor;
        selectColor = mSelectColor;
    }


    /**
     * @return 导航条高度，单位：dp
     */
    public abstract int initBottomHeight();

    /**
     * 导航条的背景选择器，按顺序int数组
     *
     * @return
     */
    public abstract int[] initBottomSelector();

    public abstract String[] initBottomStrs();

    public abstract BaseFragment[] initFragments();

    @Override
    public int initRootLayout() {
        return R.layout.main;
    }

    @Override
    public void initViews() {
        LinearLayout mLinearLayout = new LinearLayout(mActivitySelf);
        LinearLayout.LayoutParams mRootLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mLinearLayout.setLayoutParams(mRootLayoutParams);
        mLinearLayout.setOrientation(LinearLayout.VERTICAL);
        FrameLayout mFrameLayout = new FrameLayout(mActivitySelf);
        LinearLayout.LayoutParams mLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0);
        mLayoutParams.weight = 1;
        mFrameLayout.setId(R.id.main_content);
        mFrameLayout.setLayoutParams(mLayoutParams);
        mRadioGroup = new RadioGroup(mActivitySelf);
        RadioGroup.LayoutParams mRGLayoutParams = new RadioGroup.LayoutParams(RadioGroup.LayoutParams.MATCH_PARENT, (int) TDevice.dp2px(initBottomHeight()));
        mRadioGroup.setLayoutParams(mRGLayoutParams);
        mRadioGroup.setOrientation(LinearLayout.HORIZONTAL);
        int mNum = initBottomNum();
        mBaseFragments = initFragments();
        List<BaseFragment> mList = Arrays.asList(mBaseFragments);
        mAll = new ArrayList<>(mList);
        int[] mDrs = initBottomSelector();
        String[] mStrs = initBottomStrs();
        try {
            for (int i = 0; i < mNum; i++) {
                RadioButton mRadioButton = new RadioButton(mActivitySelf);
                RadioGroup.LayoutParams mRbLayoutParams = new RadioGroup.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
                mRbLayoutParams.weight = 1;
                mRbLayoutParams.bottomMargin = (int) TDevice.dp2px(2);
                mRbLayoutParams.topMargin = (int) TDevice.dp2px(5);
                mRadioButton.setLayoutParams(mRbLayoutParams);
                mRadioButton.setTextColor(getResources().getColor(oriColor));
                if (i == 0) {
                    mShowFragment = mBaseFragments[0];
//                    mRadioButton.setTextColor(getResources().getColor(selectColor));
                }
//                mRadioButton.setChecked(true);
                mRadioButton.setMaxLines(1);
                mRadioButton.setText(mStrs[i]);
                mRadioButton.setButtonDrawable(null);
                mRadioButton.setBackgroundColor(getResources().getColor(R.color.white));
                mRadioButton.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM);
                Drawable drawable = this.getResources().getDrawable(mDrs[i]);
                mRadioButton.setCompoundDrawablesRelativeWithIntrinsicBounds(null, drawable, null, null);
//                drawableTop
//                Drawable[] mRds = mRadioButton.getCompoundDrawables();
//                Rect r = new Rect(0, 0, mRds[1].getMinimumWidth() / 3, mRds[1].getMinimumHeight() / 3);
//                mRds[1].setBounds(r);
//                mRadioButton.setCompoundDrawables(null, mRds[1], null, null);
                mRadioButton.setOnClickListener(this);
                mRadioGroup.addView(mRadioButton);
                mRbs.add(mRadioButton);
            }
            mLinearLayout.addView(mFrameLayout);
            mLinearLayout.addView(mRadioGroup);
            setContentView(mLinearLayout);
            addFrag(R.id.main_content, mShowFragment);
            showFrag(mShowFragment);
            mRbs.get(0).setChecked(true);
            mRbs.get(0).setTextColor(getResources().getColor(selectColor));;

        } catch (IndexOutOfBoundsException e) {
            Toast.makeText(mActivitySelf, getResources().getString(R.string.indexOOB), Toast.LENGTH_SHORT).show();
//            throw new IndexOutOfBoundsException(getResources().getString(R.string.indexOOB));
        }
    }


    /**
     * //移除，添加 Fragment快捷操作
     *
     * @param showFragment
     */
    private void changeFragHS(BaseFragment showFragment) {
        if (!showFragment.isAdded()) {
            addFrag(R.id.main_content, showFragment);
            mAll.add(showFragment);
        }
        for (BaseFragment fragment : mAll) {
            if (fragment != showFragment) {
                removeFrag(fragment);
            }
        }
        showFrag(showFragment);
//        for (int i = 0; i < mAll.size(); i++) {
//            if (!mAll.get(i).equals(showFragment)) {
//                hideFrag(mAll.get(i));
//            }
//        }
    }

    /**
     * 点击按钮底部导航条改变字体颜色
     */
    private void changeTextColor() {
        for (int i = 0; i < mRadioGroup.getChildCount(); i++) {
            RadioButton mChildAt = (RadioButton) mRadioGroup.getChildAt(i);
            Log.e("xx", "第" + i + "个的状态是:" + mChildAt.isChecked());
            if (mChildAt.isChecked()) {
                mChildAt.setTextColor(getResources().getColor(selectColor));
            } else {
                mChildAt.setTextColor(getResources().getColor(oriColor));
            }
        }
        Log.e("xx", "--------------------------");

    }


    @Override
    public void onClick(View v) {
        RadioButton rb = (RadioButton) v;
        int i = mRbs.indexOf(rb);
        if (!rb.isChecked()) {
            return;
        }
        changeTextColor();
        mShowFragment = mBaseFragments[i];
        changeFragHS(mShowFragment);
    }
}
