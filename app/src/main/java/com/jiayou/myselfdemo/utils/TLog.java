package com.jiayou.myselfdemo.utils;

import android.util.Log;

import com.jiayou.myselfdemo.Constant;


/**
 * Created by Malik J on 2017/8/24.
 */

public class TLog {
    private static final String LOG_TAG = "ranger";


    private TLog() {
    }

    public static void error(String log) {
        if (Constant.isDebug) Log.e(LOG_TAG, "" + log);
    }

    public static void log(String log) {
        if (Constant.isDebug) Log.i(LOG_TAG, log);
    }

    public static void log(String tag, String log) {
        if (Constant.isDebug) Log.i(tag, log);
    }

    public static void d(String tag, String log) {
        if (Constant.isDebug) Log.d(tag, log);
    }

    public static void e(String tag, String log) {
        if (Constant.isDebug) Log.e(tag, log);
    }

    public static void i(String tag, String log) {
        if (Constant.isDebug) Log.i(tag, log);
    }
}
