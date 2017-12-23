package com.myself.library.view;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.TextView;

import com.myself.library.R;


public class TipsCutdownDialog extends Dialog {


    public LayoutParams layoutParams;
    private int mWithMeasureMode = LayoutParams.WRAP_CONTENT;
    private int mHeightMeasureMode = LayoutParams.WRAP_CONTENT;
    public int mGravity = Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL;
    private Context mContext;
    private View mRootVoew;
    private TextView mTvTitle;
    private TextView tvCutdwon;
    private TextView btnCancel;
    private View btnSave;

    private TextView tv_des;
    private String title;
    private String des;
    private String cancelText;


    protected CountDownTimer mTimer;
    private OnSelectItemListener mOnSelectItemListener;
    public long time = 30 * 1000;
    public boolean isCutDown = false;


    public interface OnSelectItemListener {
        void Onclick(Dialog dialog, boolean isOk);
    }

    public void setmOnSelectItemListener(OnSelectItemListener onSelectItemListener) {
        mOnSelectItemListener = onSelectItemListener;
    }

    public TipsCutdownDialog getInstance() {
        return this;
    }

    public TipsCutdownDialog(Context context) {
        super(context, R.style.AddressStyle);
        mContext = context;

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRootVoew = View.inflate(mContext, R.layout.dialog_tips, null);
        setContentView(mRootVoew);
        //
        initOption();


        tvCutdwon = (TextView) mRootVoew.findViewById(R.id.tv_cutdwon);
        btnCancel = (TextView) mRootVoew.findViewById(R.id.btn_cancel);
        btnSave = mRootVoew.findViewById(R.id.btn_save);
        mTvTitle = (TextView) mRootVoew.findViewById(R.id.tv_title);
        tv_des = (TextView) mRootVoew.findViewById(R.id.tv_des);


        //
        if (isCutDown) {
            tvCutdwon.setVisibility(View.VISIBLE);
            cutdown();
        }

        if (!TextUtils.isEmpty(title)) {
            mTvTitle.setText(title + "");
        }

        if (!TextUtils.isEmpty(des)) {
            tv_des.setText(des + "");
        }


        if (!TextUtils.isEmpty(cancelText)) {
            btnCancel.setText(cancelText + "");
        }


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnSelectItemListener != null) {

                    mOnSelectItemListener.Onclick(getInstance(), true);

                }
            }
        });


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnSelectItemListener != null) {

                    mOnSelectItemListener.Onclick(getInstance(), false);

                }
            }
        });

    }

    /*
    *
    * */
    public void setTvTitle(String tvTitle) {

        this.title = tvTitle;
    }

    /*
*
* */
    public void setTvDes(String des) {

        this.des = des;
    }

    /*
*
* */
    public void setCancelText(String cancelText) {

        this.cancelText = cancelText;
    }


    /*
*
*
*
* */
    public void setCutdowmTime(boolean isCutdowwn, long timelong) {

        this.isCutDown = isCutdowwn;
        this.time = timelong * 1000;
    }


    /*
    *
    * 倒计时
    * */
    private void cutdown() {

        if (tvCutdwon.getTag() == null) {
            tvCutdwon.setAlpha(0.6f);
            tvCutdwon.setTag(true);

            mTimer = new CountDownTimer(time, 1000) {
                @SuppressLint("DefaultLocale")
                @Override
                public void onTick(long millisUntilFinished) {
                    tvCutdwon.setText(String.format("%s%s%d%s", "", "(", millisUntilFinished / 1000, ")"));
                }

                @Override
                public void onFinish() {
                    tvCutdwon.setTag(null);
                    tvCutdwon.setText("获取验证码");
                    tvCutdwon.setAlpha(1.0f);
                    btnCancel.callOnClick();


                }
            };

            mTimer.start();
        }

    }


    /*
    *
    *
    * */
    private void initOption() {
        Window window = getWindow();
        layoutParams = window.getAttributes();
        layoutParams.width = mWithMeasureMode;
        layoutParams.height = mHeightMeasureMode;
        layoutParams.gravity = mGravity;
    }


    /*
    *   设置view的 测量模式
    * */
    public void setMeasureMode(int withMeasureMode, int heightMeasureMode) {
        mWithMeasureMode = withMeasureMode;
        mHeightMeasureMode = heightMeasureMode;
    }


    public static class Builder {

        private Context mContext;
        private OnSelectItemListener mOnItemClickLitener;
        private String mTvTitle;
        private String des;
        private String cancelText;
        private TipsCutdownDialog mSelectDialog;
        private boolean cancelable = true;
        private boolean iscutDown = false;
        private long timelong = 30 * 1000;


        public Builder(Context context) {
            mContext = context;
            mSelectDialog = new TipsCutdownDialog(this.mContext);
        }


        public Builder context(Context context) {
            this.mContext = context;
            return this;
        }

        public Builder onItemClickLitener(OnSelectItemListener onItemClickLitener) {
            this.mOnItemClickLitener = onItemClickLitener;
            return this;
        }


        public Builder title(String tvTitle) {
            this.mTvTitle = tvTitle;
            return this;
        }

        public Builder cancelText(String cancelText) {
            this.cancelText = cancelText;
            return this;
        }

        public Builder des(String des) {
            this.des = des;
            return this;
        }


        public Builder setCancelable(boolean cancelable) {
            this.cancelable = cancelable;
            return this;
        }


        /*
        *
        * timelong : 单位秒
        *
        * */
        public Builder setCutdowmTime(boolean iscutDown, long timelong) {
            this.iscutDown = iscutDown;
            this.timelong = timelong;
            return this;
        }


        public Builder build() {

            if (!TextUtils.isEmpty(mTvTitle)) {
                mSelectDialog.setTvTitle(this.mTvTitle);
            }

            if (!TextUtils.isEmpty(des)) {
                mSelectDialog.setTvDes(this.des);
            }

            if (!TextUtils.isEmpty(cancelText)) {
                mSelectDialog.setCancelText(this.cancelText);
            }


            if (iscutDown) {
                mSelectDialog.setCutdowmTime(true, timelong);
            }

            mSelectDialog.setmOnSelectItemListener(this.mOnItemClickLitener);
            this.mSelectDialog.setCancelable(cancelable);

            return this;
        }


        public Builder show() {
            mSelectDialog.show();
            return this;
        }

        public Builder dismiss() {
            mSelectDialog.dismiss();
            return this;
        }


        public TipsCutdownDialog dialog() {
            return mSelectDialog;
        }

    }

}
