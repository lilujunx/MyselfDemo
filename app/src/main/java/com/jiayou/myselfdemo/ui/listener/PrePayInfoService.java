package com.jiayou.myselfdemo.ui.listener;

import com.jiayou.myselfdemo.entity.RepayEntity;
import com.jiayou.myselfdemo.entity.TempEntity;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * 预支付接口定义
 */

public interface PrePayInfoService {


//    @GET("xxxxxxxxxx")
//    @Headers("Content-Type:application/json")
//    Call<String> getPrePayInfo();
//
//
//    @POST("xxxxxxxxxx")
//    @Headers("Content-Type:application/json")
//    Call<String> postPrePayInfo();

    @GET("tuserrepayment/userPayment")
    @Headers("Content-Type:application/json")
    Call<TempEntity> getPrePayInfo(@Header("token") String token);



    @POST("tuserrepayment/userPayment")
    @Headers("Content-Type:application/json")
    Call<TempEntity> postPrePayInfo(@Header("token") String token, @Body RepayEntity mMap);
}