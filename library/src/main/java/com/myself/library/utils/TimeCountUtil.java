package com.myself.library.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.CountDownTimer;
import android.widget.Button;


/**
 *倒计时60秒，获取验证码用的
 */

public class TimeCountUtil extends CountDownTimer {
    private Activity mActivity;
    private Button btn;//按钮
    private long clickTime;

    private static TimeCountUtil mTimeCountUtil;

    public TimeCountUtil getInstance(Activity mActivity, long millisInFuture, long countDownInterval, Button btn,long clickTime) {
        if (mTimeCountUtil == null) {
            synchronized (TimeCountUtil.class) {
                if (mTimeCountUtil == null) {
                    return new TimeCountUtil(mActivity, millisInFuture, countDownInterval, btn,clickTime);
                }
            }
        }
        return mTimeCountUtil;
    }


    // 在这个构造方法里需要传入三个参数，一个是Activity，一个是总的时间millisInFuture，一个是countDownInterval，然后就是你在哪个按钮上做这个是，就把这个按钮传过来就可以了
    public TimeCountUtil(Activity mActivity, long millisInFuture, long countDownInterval, Button btn) {
        super(millisInFuture, countDownInterval);
        this.mActivity = mActivity;
        this.btn = btn;
    }

    // 在这个构造方法里需要传入三个参数，一个是Activity，一个是总的时间millisInFuture，一个是countDownInterval，然后就是你在哪个按钮上做这个是，就把这个按钮传过来就可以了
    public TimeCountUtil(Activity mActivity, long millisInFuture, long countDownInterval, Button btn, long clickTime) {
        super(millisInFuture, countDownInterval);
        this.mActivity = mActivity;
        this.clickTime = clickTime;
        this.btn = btn;
        long now=System.currentTimeMillis();

    }

    @SuppressLint("NewApi")
    @Override
    public void onTick(long millisUntilFinished) {
        btn.setEnabled(false);//设置不能点击
        btn.setText(millisUntilFinished / 1000 + "S");//设置倒计时时间
        //设置按钮为灰色，这时是不能点击的
//        btn.setBackgroundColor((Color.parseColor("#e7e9ea")));
//        Spannable span = new SpannableString(btn.getText().toString());//获取按钮的文字
//        span.setSpan(new ForegroundColorSpan(Color.RED), 0, 2, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);//讲倒计时时间显示为红色
//        btn.setText(span);

    }


    @SuppressLint("NewApi")
    @Override
    public void onFinish() {
        btn.setText("获取验证码");
        btn.setEnabled(true);//重新获得点击
//        btn.setBackgroundColor(mActivity.getResources().getColor(R.color.app_style));//还原背景色
    }
}