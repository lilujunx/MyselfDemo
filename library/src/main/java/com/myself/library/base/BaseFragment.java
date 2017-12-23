package com.myself.library.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.myself.library.R;
import com.myself.library.control.FragmentControl;
import com.myself.library.listener.OnConfirmClickListener;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.pedant.SweetAlert.SweetAlertDialog;


/**
 * Created by Administrator on 2016/10/21.
 */
public abstract class BaseFragment extends Fragment {

    public String mTag;
    public BaseActivity mActivitySelf;
    public LayoutInflater mLayoutInflater;
    public FragmentManager mFragmentManager;
    public View mViewRoot;
    public LinearLayout mLlTitleLeft, mLlTitleRight, mLlTitleRightRight;
    public TextView mTitleCenter;
    public Button mTitleLeft, mTitleRight, mTitleRightRight;
    private Unbinder unbinder;
    private SweetAlertDialog mSweetAlertDialog;

    public BaseFragment() {
        mTag = this.getClass().getSimpleName();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentControl.add(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        FragmentControl.remove(this);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mActivitySelf = (BaseActivity) this.getActivity();
        mLayoutInflater = LayoutInflater.from(mActivitySelf);
        mFragmentManager = this.getFragmentManager();


        int rootLayout = initRootLayout();
        mViewRoot = mLayoutInflater.inflate(rootLayout, container, false);


        if (TitleBarConfig.isUseTitleBar && isUseTitleBar()) {
            //需要使用标题栏
            addTitleBar(rootLayout);
        }
        unbinder = ButterKnife.bind(this, mViewRoot);
        initViews();
        initDatas();
        return mViewRoot;
    }

    //当前Activity是不是要标题栏
    public boolean isUseTitleBar() {
        return false;
    }

    private void addTitleBar(int rootLayout) {
        LinearLayout linearLayout = new LinearLayout(mActivitySelf);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setOrientation(LinearLayout.VERTICAL);


        View titleView = mLayoutInflater.inflate(TitleBarConfig.titleBarResID, linearLayout, false);
        linearLayout.addView(titleView);

        linearLayout.addView(mViewRoot);


        mViewRoot = linearLayout;
        mTitleLeft = (Button) mViewRoot.findViewById(R.id.title_left);
        mLlTitleLeft = (LinearLayout) mViewRoot.findViewById(R.id.ll_title_left);
        mTitleCenter = (TextView) mViewRoot.findViewById(R.id.title_center);
        mTitleRight = (Button) mViewRoot.findViewById(R.id.title_right);
        mLlTitleRight = (LinearLayout) mViewRoot.findViewById(R.id.ll_title_right);
        mTitleRightRight = (Button) mViewRoot.findViewById(R.id.title_right_right);
        mLlTitleRightRight = (LinearLayout) mViewRoot.findViewById(R.id.ll_title_right_right);
        if (mTitleLeft != null) {
            mTitleLeft.setVisibility(View.INVISIBLE);
        }
        if (mTitleRight != null) {
            mTitleRight.setVisibility(View.INVISIBLE);
        }


    }

    public View findViewById(int resId) {
        return mViewRoot.findViewById(resId);
    }


    public void setTitleLeft(String text, View.OnClickListener onClickListener) {
        if (mLlTitleLeft != null) {
            mTitleLeft.setVisibility(View.VISIBLE);
            mTitleLeft.setText(text);
            if (onClickListener != null) {
                mLlTitleLeft.setOnClickListener(onClickListener);
            }
        }
    }

    public void setTitleLeft(String text) {
        setTitleLeft(text, null);
    }

    public void setTitleLeft(int resId, View.OnClickListener onClickListener) {
        if (mLlTitleLeft != null) {
            mTitleLeft.setVisibility(View.VISIBLE);
            mTitleLeft.setBackgroundResource(resId);
            if (onClickListener != null) {
                mLlTitleLeft.setOnClickListener(onClickListener);
            }

        }

    }


    /**
     * @param text             右标题前一个
     * @param text2            右标题后一个
     * @param onClickListener  前一个点击事件
     * @param onClickListener2 后一个点击事件
     */
    public void setTitleRight(String text, String text2, View.OnClickListener onClickListener, View.OnClickListener onClickListener2) {
        if (text != null && mLlTitleRight != null) {
            mLlTitleRight.setVisibility(View.VISIBLE);
            mTitleRight.setText(text);
            if (onClickListener != null) {
                mLlTitleRight.setOnClickListener(onClickListener);
            }
        }
        if (text2 != null && mLlTitleRightRight != null) {
            mLlTitleRightRight.setVisibility(View.VISIBLE);
            mTitleRightRight.setText(text2);
            if (onClickListener2 != null) {
                mLlTitleRightRight.setOnClickListener(onClickListener2);
            }
        }
    }
    public void setTitleRight(String text) {
        setTitleRight(text, null, null, null);
    }
    public void setTitleRight(String text, View.OnClickListener mOnClickListener) {
        setTitleRight(text, null, mOnClickListener, null);
    }
    public void setTitleRightRight(String text) {
        setTitleRight(null, text, null, null);
    }
    public void setTitleRightRight(String text, View.OnClickListener mOnClickListener) {
        setTitleRight(null, text, null, mOnClickListener);
    }


    /**
     * @param resId            右标题前一个
     * @param resId1           右标题后一个
     * @param onClickListener  前一个点击事件
     * @param onClickListener1 后一个点击事件
     */
    public void setTitleRight(int resId, int resId1, View.OnClickListener onClickListener, View.OnClickListener onClickListener1) {
        if (resId != 0 && mLlTitleRight != null) {
            mLlTitleRight.setVisibility(View.VISIBLE);
            mTitleRight.setBackground(getResources().getDrawable(resId));
            if (onClickListener != null) {
                mLlTitleRight.setOnClickListener(onClickListener);
            }
        }
        if (resId1 != 0 && mLlTitleRightRight != null) {
            mLlTitleRightRight.setVisibility(View.VISIBLE);
            mTitleRightRight.setBackground(getResources().getDrawable(resId1));
            if (onClickListener1 != null) {
                mLlTitleRightRight.setOnClickListener(onClickListener1);
            }
        }
    }
    public void setTitleRight(int resId) {
        setTitleRight(resId, 0, null, null);
    }
    public void setTitleRight(int resId, View.OnClickListener mOnClickListener) {
        setTitleRight(resId, 0, mOnClickListener, null);
    }
    public void setTitleRightRight(int resId){
        setTitleRight(0, resId, null, null);
    }
    public void setTitleRightRight(int resId, View.OnClickListener mOnClickListener){
        setTitleRight(0, resId, null, mOnClickListener);
    }


    public void setTitleCenter(String text) {
        if (mTitleCenter != null) {
            mTitleCenter.setText(text);
        }
    }










    public abstract int initRootLayout();

    public abstract void initViews();

    public abstract void initDatas();

    /**
     * 启动Activity
     **/
    public void goToActivity(Class activity) {
        goToActivity(activity, null, null);
    }

    public void goToActivity(Class activity, String key, Bundle bundle) {
        Intent intent = new Intent(mActivitySelf, activity);

        if (key != null && bundle != null) {
            intent.putExtra(key, bundle);
        }

        this.startActivity(intent);
    }

    /**启动Activity**/


    /**
     * 启动Service
     **/
    public void goToService(Class service) {
        goToService(service, null, null);
    }

    public void goToService(Class service, String key, Bundle bundle) {
        Intent intent = new Intent(mActivitySelf, service);

        if (key != null && bundle != null) {
            intent.putExtra(key, bundle);
        }

        mActivitySelf.startService(intent);
    }

    /**启动Service**/


    /**
     * 快捷操作Fragmnet
     **/
    public void addFrag(int desId, BaseFragment fragment) {

        FragmentManager fragmentManager = this.getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(desId, fragment, fragment.mTag);
        transaction.commit();

//        fragmentManager.findFragmentByTag()
    }

    public void removeFrag(BaseFragment fragment) {

        FragmentManager fragmentManager = this.getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.remove(fragment);
        transaction.commit();

    }

    public void replaceFrag(int desId, BaseFragment fragment) {

        FragmentManager fragmentManager = this.getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(desId, fragment, fragment.mTag);
        transaction.commit();

//        fragmentManager.findFragmentByTag()
    }

    public void hideFrag(BaseFragment fragment) {

        FragmentManager fragmentManager = this.getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.hide(fragment);
        transaction.commit();

    }

    public void showFrag(BaseFragment fragment) {

        FragmentManager fragmentManager = this.getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.show(fragment);
        transaction.commit();

    }
    /**快捷操作Fragmnet**/




    /**
     * @param mTitle           标题
     * @param msg              提示内容
     * @param mType            SweetAlertDialog.SUCCESS_TYPE  等
     * @param confirmStr       确定按钮文字，可不传，默认为“确认”
     * @param cancelStr        取消按钮文字，可不传，默认为“取消”
     * @param mConfirmListener 确定按钮监听
     * @param mCancelListener  取消按钮监听
     */
    protected SweetAlertDialog tips(String mTitle, String msg, int mType, String confirmStr, String cancelStr, OnConfirmClickListener mConfirmListener, SweetAlertDialog.OnCancelListener mCancelListener) {
        mSweetAlertDialog = new SweetAlertDialog(mActivitySelf, mType);
        mSweetAlertDialog.setCancelable(false);
        mSweetAlertDialog.setTitleText(mTitle)
                .setContentText(msg);
        if (TextUtils.isEmpty(confirmStr)) {
            mSweetAlertDialog.setConfirmText("确定");
        } else {
            mSweetAlertDialog.setConfirmText(confirmStr);
        }
        if (mConfirmListener != null) {
            mSweetAlertDialog.setConfirmClickListener(mConfirmListener);
        } else {
            mSweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                @Override
                public void onClick(SweetAlertDialog sDialog) {
                    sDialog.dismissWithAnimation();
                    sDialog.cancel();
                    mSweetAlertDialog = null;
                }
            });
        }
        if (mCancelListener != null) {
            if (TextUtils.isEmpty(cancelStr)) {
                mSweetAlertDialog.setCancelText("取消");
            } else {
                mSweetAlertDialog.setCancelText(cancelStr);
            }
            mSweetAlertDialog.setOnCancelListener(mCancelListener);
        }

        mSweetAlertDialog.show();
        return mSweetAlertDialog;
    }

    public SweetAlertDialog tips(String mTitle, String msg, int mType, String confirmStr, OnConfirmClickListener mConfirmListener) {
        return tips(mTitle, msg, mType, confirmStr, "", mConfirmListener, null);
    }

    public SweetAlertDialog tips(String mTitle, String msg, int mType) {
        return tips(mTitle, msg, mType, "", "", null, null);
    }

    public SweetAlertDialog tips(String mTitle, String msg, int mType, String cancelStr, SweetAlertDialog.OnCancelListener mCancelListener) {
        return tips(mTitle, msg, mType, "", cancelStr, null, mCancelListener);
    }

}

