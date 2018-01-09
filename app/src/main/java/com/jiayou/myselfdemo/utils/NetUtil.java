package com.jiayou.myselfdemo.utils;


/*
 * NetUtil
 */

import com.jiayou.myselfdemo.Constant;
import com.jiayou.myselfdemo.ui.listener.NetService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class NetUtil {

    private static NetService mNetService;

    public static NetService getInstance() {
        if (mNetService == null) {
            synchronized (NetUtil.class) {
                mNetService = getRetrofitCall(NetService.class);
            }
        }
        return mNetService;
    }

    public static NetService getRetrofitCall(Class<NetService> service) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constant.BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(service);
    }

//    public static <T> T getRetrofitCall(Class<T> service) {
//        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constant.BASE_URL)
//                .addConverterFactory(ScalarsConverterFactory.create())
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        return (T) retrofit.create(service);
//    }

}
