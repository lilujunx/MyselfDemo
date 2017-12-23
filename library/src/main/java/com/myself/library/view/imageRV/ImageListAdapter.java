package com.myself.library.view.imageRV;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.myself.library.R;

import java.util.HashMap;
import java.util.Map;


/**
 * 预留ID imageRv_image  和 imageRv_cb
 * 使用ImageView 和CheckBox
 */
public abstract class ImageListAdapter extends BaseRecyclerViewAdapter<ImageListAdapter.ImageHolder, ImageInfo> {

    private static final String TAG = "ImageListAdapter";

    private int mHeight = 0;
    private int mMaxWidth;
    private final Map<String, Target> mTargetMap;
    private View.OnClickListener mOnClickListener;


    public ImageListAdapter setOnClickListener(View.OnClickListener mOnClickListener) {
        this.mOnClickListener = mOnClickListener;
        return this;
    }

    public ImageListAdapter(Context context) {
        super(context);
        mMaxWidth = ImageRvUtils.dip2px(context, 300);
        mTargetMap = new HashMap<>();
    }


    public void setHeight(int height) {
        Log.i(TAG, "height:" + height);
        mHeight = height;
        notifyDataSetChanged();
    }

   public int getHeight() {
        return mHeight;
    }

    @Override
    public ImageListAdapter.ImageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ImageListAdapter.ImageHolder(mInflater.inflate(initItemLayout(), parent, false));
    }

    /**
     * 设置item样式的layout
     *
     * @return
     */
    public abstract int initItemLayout();

    /**
     * 设置图片默认背景
     *
     * @return
     */
    public abstract int initPicBgColor();

    @Override
    public void onBindViewHolder(final ImageListAdapter.ImageHolder holder, int position) {
        if (mHeight == 0) return;

        final ImageInfo imageInfo = mDataList.get(position);
        holder.mImageIv.setImageResource(initPicBgColor());
        if (imageInfo.getHeight() != mHeight) {
            if (imageInfo.getHeight() == 0) {
                imageInfo.setHeight(mHeight);
                imageInfo.setWidth(mHeight);
                imageInfo.setNeedResize(true);
            } else {
                int width = mHeight * imageInfo.getWidth() / imageInfo.getHeight();
                imageInfo.setWidth(Math.min(width, mMaxWidth));
                imageInfo.setHeight(mHeight);
            }
        }

        resizeImageView(holder.mImageIv, imageInfo);
        holder.mImageIv.setTag(imageInfo.getUri().getPath());

        SimpleTarget<Bitmap> mBitmapSimpleTarget = new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                if (holder.mImageIv.getTag().equals(imageInfo.getUri().getPath())) {
                    if (imageInfo.isNeedResize()) {
                        imageInfo.setHeight(resource.getHeight());
                        imageInfo.setWidth(resource.getWidth());
                        resizeImageView(holder.mImageIv, imageInfo);
                        imageInfo.setNeedResize(false);
                    }
                    holder.mImageIv.setImageBitmap(resource);
                }
            }
        };
        mTargetMap.put(imageInfo.getUri().toString(), mBitmapSimpleTarget);
        Glide.with(mContext).load(imageInfo.getUri()).asBitmap().override(imageInfo.getWidth(), imageInfo.getHeight()).centerCrop().into(mTargetMap.get(imageInfo.getUri().toString()));
        holder.mCb.setChecked(mDataList.get(position).isChecked());
        holder.mCb.setTag(mDataList.get(position));
        if (mOnClickListener != null) {
            holder.mCb.setOnClickListener(mOnClickListener);
        }
    }

    private static void resizeImageView(ImageView imageView, ImageInfo imageInfo) {
        ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
        layoutParams.height = imageInfo.getHeight();
        layoutParams.width = imageInfo.getWidth();
        imageView.setLayoutParams(layoutParams);
    }

    class ImageHolder extends RecyclerView.ViewHolder {

        ImageView mImageIv;
        CheckBox mCb;


        ImageHolder(View itemView) {
            super(itemView);
            mImageIv = (ImageView) itemView.findViewById(R.id.imageRv_image);
            mCb = (CheckBox) itemView.findViewById(R.id.imageRv_cb);

        }
    }
}
