package com.myself.library.utils;

import android.app.Activity;

import com.myself.library.listener.IPermissionListenerListener;
import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * 权限工具类
 */

public class PermissionUtil {
    private final IPermissionListenerListener mPermissionListener;


    public PermissionUtil(IPermissionListenerListener l) {
        mPermissionListener = l;
    }

    /**
     * 提示用户开启权限
     * @param activity
     * @param permissions 可以传入多个权限
     */
    public void permission(Activity activity, String... permissions) {
        RxPermissions rxPermissions = new RxPermissions(activity);
        rxPermissions.request(permissions)
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Boolean value) {
                        if (mPermissionListener != null) {
                            mPermissionListener.onNext(value);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
