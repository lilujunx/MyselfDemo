package com.myself.library.utils;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


/**
 * Created by Administrator on 2016/11/18.
 */

public class NetManager {

    private static Retrofit mRetrofit;

    public static Retrofit getInstance(String Base_url) {
        if (mRetrofit == null) {
            synchronized (NetManager.class) {
                if (mRetrofit == null) {
                    mRetrofit = new Retrofit.Builder().baseUrl(Base_url)
                            .addConverterFactory(ScalarsConverterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                }
            }
        }
        return mRetrofit;
    }


    public static <T> T getRetrofitCall(Class<T> service) {
        return (T) mRetrofit.create(service);
    }


}
