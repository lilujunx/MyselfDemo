package com.myself.library.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

/**
 * Created by admin on 2017/4/28.
 */

public class GlideUtils {

    //获取圆形图片    ---->url
    public static void getCircular(final Context context, String url, final ImageView imgv) {
        Glide.with(context).load(url).asBitmap().centerCrop().into(new BitmapImageViewTarget(imgv) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                imgv.setImageDrawable(circularBitmapDrawable);
            }
        });
    }

    //获取圆形图片    ---->id
    public static void getCircular(final Context context, int resId, final ImageView imgv) {
        Glide.with(context).load(resId).asBitmap().centerCrop().into(new BitmapImageViewTarget(imgv) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                imgv.setImageDrawable(circularBitmapDrawable);
            }
        });
    }

    //    获取圆角图片    ---->url
    public static void getRoundImg(Context context, String url, int dp, View target) {
        Glide.with(context).load(url).transform(new GlideRoundTransform(context, dp)).into((ImageView) target);
    }

    //    获取圆角图片    ---->id
    public static void getRoundImg(Context context, int resId, int dp, View target) {
        Glide.with(context).load(resId).transform(new GlideRoundTransform(context, dp)).into((ImageView) target);
    }
}
