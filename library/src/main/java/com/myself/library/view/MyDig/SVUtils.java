package com.myself.library.view.MyDig;

import android.content.Context;

/**
 *
 */

public class SVUtils {
    private static SVProgressHUD mSVProgressHUD;

    public static SVProgressHUD getInstance(Context mContext) {
        if (mSVProgressHUD == null) {
            synchronized (SVUtils.class) {
                mSVProgressHUD = new SVProgressHUD(mContext);
            }
        }
        return mSVProgressHUD;
    }



}
