package com.myself.library.listener;

import android.view.View;

/**
 * 网络请求的回调
 */

public interface IModelChangeListener {
    void onSuccess(int action, Object bean);

    void onSuccess(int action, Object bean, View tv, int position);

    void onFailure(int action, Exception e);
}
