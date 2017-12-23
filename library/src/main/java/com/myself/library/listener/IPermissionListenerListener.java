package com.myself.library.listener;

/**
 * 动态权限,用户是否同意开启权限的回调
 */

public interface IPermissionListenerListener {
    /**
     * @param agree true 表示同意
     */
    void onNext(boolean agree);
}
