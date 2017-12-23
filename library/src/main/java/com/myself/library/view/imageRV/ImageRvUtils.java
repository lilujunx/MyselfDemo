package com.myself.library.view.imageRV;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


/**
 * util set
 * Created by ldd on 2016/11/7.
 */

public class ImageRvUtils {

    public static int MAX_IMAGE = 50;

    public static <T> T checkNotNull(T reference) {
        if (reference == null) {
            throw new NullPointerException();
        }
        return reference;
    }

    public static <T> T checkNotNull(T reference, String cause) {
        if (reference == null) {
            throw new NullPointerException(cause);
        }
        return reference;
    }

    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public static void  loadImage(final Activity mContext, final ImageListAdapter mImageListAdapter) {
        final List<ImageInfo> mImageInfos = new ArrayList<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Cursor mCursor = mContext.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        new String[]{MediaStore.Images.Media._ID, MediaStore.Images.Media.DATA,
                                MediaStore.Images.Media.WIDTH, MediaStore.Images.Media.HEIGHT},
                        MediaStore.Images.Media.MIME_TYPE + "=? OR " + MediaStore.Images.Media.MIME_TYPE + "=?",
                        new String[]{"image/jpeg", "image/png"}, MediaStore.Images.Media._ID + " DESC");

                if (mCursor == null) return;
                // Take 100 images
                while (mCursor.moveToNext() && mImageInfos.size() < MAX_IMAGE) {
                    long id = mCursor.getLong(mCursor.getColumnIndex(MediaStore.Images.Media._ID));

                    String path = mCursor.getString(mCursor.getColumnIndex(MediaStore.Images.Media.DATA));
                    int height = mCursor.getInt(mCursor.getColumnIndex(MediaStore.Images.Media.HEIGHT));
                    int width = mCursor.getInt(mCursor.getColumnIndex(MediaStore.Images.Media.WIDTH));
                    Uri uri = Uri.fromFile(new File(path));
                    ImageInfo imageInfo = new ImageInfo(uri, width, height);
                    mImageInfos.add(imageInfo);
                }
                mCursor.close();
                mContext.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mImageListAdapter.addAllData(mImageInfos);
                        mImageListAdapter.notifyDataSetChanged();
                    }
                });
            }
        }).start();
    }
}
