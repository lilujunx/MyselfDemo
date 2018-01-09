/*
 * Copyright (C) 2015, 程序亦非猿
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.myself.library.view.love;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.myself.library.R;
import com.myself.library.utils.DensityUtil;

import java.util.Random;


public class PeriscopeLayout extends RelativeLayout {

    private Interpolator line = new LinearInterpolator();//线性
    private Interpolator acc = new AccelerateInterpolator();//加速
    private Interpolator dce = new DecelerateInterpolator();//减速
    private Interpolator accdec = new AccelerateDecelerateInterpolator();//先加速后减速
    private Interpolator[] interpolators;

    /**
     * 布局高度
     */
    private int mHeight;
    /**
     * 布局宽度
     */
    private int mWidth;
    private LayoutParams lp;
    private Drawable[] drawables;
    private Random random = new Random();

    private int dHeight;        //图片高度
    private int dWidth;         //图片宽度

    public PeriscopeLayout(Context context) {
        super(context);
        init();
    }

    public PeriscopeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PeriscopeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public PeriscopeLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {

        //初始化显示的图片
        drawables = new Drawable[3];
        Drawable red = getResources().getDrawable(R.drawable.pl_red);
        Drawable yellow = getResources().getDrawable(R.drawable.pl_yellow);
        Drawable blue = getResources().getDrawable(R.drawable.pl_blue);

        drawables[0] = red;
        drawables[1] = yellow;
        drawables[2] = blue;
        //获取图的宽高 用于后面的计算
        //注意 我这里3张图片的大小都是一样的,所以我只取了一个
        dHeight = red.getIntrinsicHeight();
        dWidth = red.getIntrinsicWidth();

        //底部 并且 水平居中
        lp = new LayoutParams(dWidth, dHeight);
//       todo 底部居中
        lp.addRule(CENTER_HORIZONTAL, TRUE);
        lp.addRule(ALIGN_PARENT_BOTTOM, TRUE);

        // 初始化插补器
        interpolators = new Interpolator[4];
        interpolators[0] = line;
        interpolators[1] = acc;
        interpolators[2] = dce;
        interpolators[3] = accdec;

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
    }


    public void addHeart() {

        ImageView imageView = new ImageView(getContext());
        //随机选一个
        imageView.setImageDrawable(drawables[random.nextInt(3)]);
        imageView.setLayoutParams(lp);

        addView(imageView);

        Animator set = getAnimator(imageView);
        set.addListener(new AnimEndListener(imageView));
        set.start();

    }


    /**
     * 点击某个View的时候，添加爱心
     *
     * @param v
     */
    public void addHeart(View v) {
        ImageView imageView = new ImageView(getContext());
        imageView.setImageDrawable(drawables[random.nextInt(3)]);
        ViewGroup.LayoutParams mLayoutParams = v.getLayoutParams();
        ViewGroup.LayoutParams mParams = new ViewGroup.LayoutParams(mLayoutParams.width, mLayoutParams.height);
        mParams.height = mLayoutParams.height - DensityUtil.dip2px(getContext(), v.getHeight());
        imageView.setLayoutParams(mParams);
        addView(imageView);
        Animator set = getAnimator(imageView, v);
        set.addListener(new AnimEndListener(imageView));
        set.start();
    }

    /**
     * 0
     * 点击某个Item的时候，添加爱心
     *
     * @param v
     */
    public void addHeart(View v, RelativeLayout.LayoutParams mLp) {
        for (int i = 0; i < 40; i++) {

            ImageView imageView = new ImageView(getContext());
            //随机选一个
            imageView.setImageDrawable(drawables[random.nextInt(3)]);
//            Log.e("xx", "v.getLeft():" + v.getLeft() + ",,,v.getTop():" + v.getTop());
//            Log.e("xx", "v.getX():" + v.getX() + ",,,v.getY():" + v.getY());
//            ViewGroup.LayoutParams mLayoutParams = v.getLayoutParams();
//            mLayoutParams.height = mLayoutParams.height - DensityUtil.dip2px(getContext(), v.getHeight());
            mLp.height = dHeight;
            mLp.width = dWidth;
            imageView.setLayoutParams(mLp);
            addView(imageView);
            Animator set = getAnimator(imageView, v, mLp);
            set.addListener(new AnimEndListener(imageView));
            set.start();
//            SystemClock.sleep(100);
//            set.setStartDelay(100);
        }
//        addView(imageView);
//        Animator set = getAnimator(imageView, v, 10086);
//        set.addListener(new AnimEndListener(imageView));
//        set.start();
    }

    /**
     * @param target
     * @return
     */
    private Animator getAnimator(View target) {
        return getAnimator(target, null, null);
    }

    private Animator getAnimator(View target, View clickView, RelativeLayout.LayoutParams mLp) {
//        set
        AnimatorSet set = getEnterAnimtor(target);
        ValueAnimator bezierValueAnimator;
        if (clickView == null) {
            bezierValueAnimator = getBezierValueAnimator(target);
        } else if (clickView != null && mLp == null) {
            bezierValueAnimator = getBezierValueAnimator(target, clickView);
        } else {
            bezierValueAnimator = getBezierValueAnimator(target, clickView, mLp);
        }
        AnimatorSet finalSet = new AnimatorSet();
        finalSet.playSequentially(set);
        finalSet.playSequentially(set, bezierValueAnimator);
        finalSet.setInterpolator(interpolators[random.nextInt(4)]);
        finalSet.setTarget(target);
        return finalSet;
    }

    private Animator getAnimator(View target, View clickView) {
        return getAnimator(target, clickView, null);
    }

    private AnimatorSet getEnterAnimtor(final View target) {

        ObjectAnimator alpha = ObjectAnimator.ofFloat(target, View.ALPHA, 0.2f, 1f);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(target, View.SCALE_X, 0.2f, 1f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(target, View.SCALE_Y, 0.2f, 1f);
        AnimatorSet enter = new AnimatorSet();
        enter.setDuration(60);
        enter.setInterpolator(new LinearInterpolator());
        enter.playTogether(alpha, scaleX, scaleY);
        enter.setTarget(target);
        return enter;
    }

    private ValueAnimator getBezierValueAnimator(View target) {
        //初始化一个贝塞尔计算器- - 传入
        BezierEvaluator evaluator = new BezierEvaluator(getPointF(2), getPointF(1));
        // 传入了起点 和 终点
        ValueAnimator animator = ValueAnimator.ofObject(evaluator, new PointF((mWidth - dWidth) / 2, mHeight - dHeight), new PointF(random.nextInt(getWidth()), 0));
        animator.addUpdateListener(new BezierListener(target));
        animator.setTarget(target);
        animator.setDuration(3000);
        return animator;
    }


    /**
     * 点击的View引发的事件
     *
     * @param target
     * @param clickView
     * @return
     */
    private ValueAnimator getBezierValueAnimator(View target, View clickView) {
        BezierEvaluator evaluator = new BezierEvaluator(getPointF(2), getPointF(1));
        float mX = clickView.getX();
        float mY = clickView.getY();
//        Log.e("xx", "这里画的坐标是:" + mX + "    " + mY);
//        Toast.makeText(getContext(), "这里画的坐标是:" + mX + "    " + mY, Toast.LENGTH_SHORT).show();
        ValueAnimator animator = ValueAnimator.ofObject(evaluator, new PointF(mX, mY - dHeight), new PointF(random.nextInt(getWidth()), 0));
        animator.addUpdateListener(new BezierListener(target));
        animator.setTarget(target);
        animator.setDuration(3000);
        return animator;
    }

    /**
     * 点击的Item引发的事件
     *
     * @param target
     * @param clickView
     * @return
     */
    private ValueAnimator getBezierValueAnimator(View target, View clickView, RelativeLayout.LayoutParams mLp) {
        BezierEvaluator evaluator = new BezierEvaluator(getPointF(2), getPointF(1));
//        int[] ins = new int[2];
//        clickView.getLocationInWindow(ins);
//        float mX = ins[0];
//        float mY = ins[1];
//        Log.e("xx", "这里画的坐标是:" + mX + "    " + mY);
//        Toast.makeText(getContext(), "getLeft:" + mX + "    ,,getTop:" + mY, Toast.LENGTH_SHORT).show();
//        Toast.makeText(getContext(), "new PointF(mX, mY):" + new PointF(mX, mY).toString() + "\n    ,,new PointF(random.nextInt(getWidth()), 0):" + new PointF(random.nextInt(getWidth()), 0).toString(), Toast.LENGTH_SHORT).show();
        ValueAnimator animator = ValueAnimator.ofObject(evaluator, new PointF(mLp.leftMargin, mLp.topMargin), new PointF(getWidth() - random.nextInt(DensityUtil.dip2px(getContext(), 100)), 0));
        animator.addUpdateListener(new BezierListener(target));
        animator.setTarget(target);
        animator.setDuration(2000);
        return animator;
    }


    /**
     * 获取中间的两个 点
     *
     * @param scale
     */
    private PointF getPointF(int scale) {
        PointF pointF = new PointF();
//        pointF.x = mWidth-random.nextInt((mWidth-1000));
        pointF.x = mWidth - random.nextInt((mWidth - 800));
//        pointF.y = 100;

        pointF.y = random.nextInt((mHeight)) / scale;

//        转折点
//        pointF.x = random.nextInt((100));
//        pointF.y = random.nextInt((100)) / scale;
        return pointF;
    }

    private class BezierListener implements ValueAnimator.AnimatorUpdateListener {

        private View target;

        public BezierListener(View target) {
            this.target = target;
        }

        @Override
        public void onAnimationUpdate(ValueAnimator animation) {
            //这里获取到贝塞尔曲线计算出来的的x y值 赋值给view 这样就能让爱心随着曲线走啦
            PointF pointF = (PointF) animation.getAnimatedValue();
            target.setX(pointF.x);
            target.setY(pointF.y);
            // 这里顺便做一个alpha动画
            target.setAlpha(1 - animation.getAnimatedFraction());
        }
    }


    private class AnimEndListener extends AnimatorListenerAdapter {
        private View target;

        public AnimEndListener(View target) {
            this.target = target;
        }

        @Override
        public void onAnimationEnd(Animator animation) {
            super.onAnimationEnd(animation);
            //因为不停的add 导致子view数量只增不减,所以在view动画结束后remove掉
            removeView((target));
        }
    }
}
