package com.myself.library.base;

import android.app.Application;
import android.content.Context;

import com.myself.library.utils.TDevice;
import com.xutils.x;


/**
 * Created by Administrator on 2016/10/21.
 */
public abstract class BaseApp extends Application {
    public static Context mContextGlobal;

    @Override
    public void onCreate() {
        super.onCreate();
        mContextGlobal=this;
        initTitleBar();
        initOthers();
        x.Ext.init(this);
        TDevice.init(this);
    }
    
    public abstract void initOthers();
    
    public abstract void initTitleBar();
    
    public  abstract  boolean isDebugMode();
    
    



}
