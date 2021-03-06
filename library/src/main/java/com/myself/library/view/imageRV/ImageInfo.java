package com.myself.library.view.imageRV;

import android.net.Uri;

public class ImageInfo {
    private final Uri mUri;
    private int mWidth;
    private int mHeight;
    private boolean mNeedResize;
    private boolean checked=false;

    public ImageInfo(Uri uri, int width, int height) {
        mUri = uri;
        mWidth = width;
        mHeight = height;
        mNeedResize = false;
    }

    public boolean isChecked() {
        return checked;
    }

    public ImageInfo setChecked(boolean mChecked) {
        checked = mChecked;
        return this;
    }

    public void setWidth(int width) {
        this.mWidth = width;
    }

    public void setHeight(int height) {
        this.mHeight = height;
    }

    public Uri getUri() {
        return mUri;
    }

    public int getWidth() {
        return mWidth;
    }

    public int getHeight() {
        return mHeight;
    }

    public boolean isNeedResize() {
        return mNeedResize;
    }

    public void setNeedResize(boolean needResize) {
        mNeedResize = needResize;
    }

    @Override
    public String toString() {
        return "Image{" +
                "mUri=" + mUri +
                ", mWidth=" + mWidth +
                ", mHeight=" + mHeight +
                '}';
    }
}
