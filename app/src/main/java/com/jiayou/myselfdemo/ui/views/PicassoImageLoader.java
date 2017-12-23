package com.jiayou.myselfdemo.ui.views;

import android.app.Activity;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jiayou.myselfdemo.R;
import com.lzy.imagepicker.loader.ImageLoader;

import java.io.File;

/**
 * Created by JY_2016 on 2017/10/9.
 */

public class PicassoImageLoader implements ImageLoader {
    @Override
    public void displayImage(Activity activity, String path, ImageView imageView, int width, int height) {
        Glide.with(activity)//
                .load(Uri.fromFile(new File(path)))//
                .placeholder(R.mipmap.default_image)//
                .error(R.mipmap.default_image)//
                .into(imageView);
    }

    @Override
    public void clearMemoryCache() {

    }
}
