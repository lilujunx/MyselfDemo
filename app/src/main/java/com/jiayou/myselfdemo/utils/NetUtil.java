package com.jiayou.myselfdemo.utils;


/*
 * NetUtil
 */

import com.jiayou.myselfdemo.Constant;
import com.jiayou.myselfdemo.ui.listener.NetService;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class NetUtil {

    private static NetService mNetService;

    /**
     * 获取普通实例
     *
     * @return
     */
    public static NetService getInstance() {
        if (mNetService == null) {
            synchronized (NetUtil.class) {
                mNetService = getRetrofitCall(NetService.class);
            }
        }
        return mNetService;
    }


    /**
     * 获取一个长连接实例，60S
     *
     * @return
     */
    public static NetService getLongConnectionInstance() {
        if (mNetService == null) {
            synchronized (NetUtil.class) {
                mNetService = getLongConnection(NetService.class);
            }
        }
        return mNetService;
    }


    private static NetService getRetrofitCall(Class<NetService> service) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constant.BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(service);
    }


    /**
     * connection
     *
     * @param service
     * @return
     */
    private static NetService getLongConnection(Class<NetService> service) {
        OkHttpClient client = new OkHttpClient.Builder().
                connectTimeout(60, TimeUnit.SECONDS).
                readTimeout(60, TimeUnit.SECONDS).
                writeTimeout(60, TimeUnit.SECONDS).build();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constant.BASE_URL)
                .client(client)
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
