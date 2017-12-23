package com.myself.library.view.MyDig;

import android.content.Context;

/**
 * Created by Malik J on 2017/12/8.
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
