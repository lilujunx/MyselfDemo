package com.jiayou.myselfdemo.pay;

import com.jiayou.myselfdemo.ui.listener.PrePayInfoService;
import com.myself.library.pay.BasePayParams;
import com.myself.library.pay.NetworkClientInterf;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 *
 */

public class RetrofitClient implements NetworkClientInterf {


    @Override
    public void get(BasePayParams payParams, final CallBack c) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(payParams.getApiUrl())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();


    }

    @Override
    public void post(BasePayParams payParams, final CallBack c) {
        PayParams mPayParams= (PayParams) payParams;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(mPayParams.getApiUrl())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PrePayInfoService service = retrofit.create(PrePayInfoService.class);
//        final RepayEntity mMap = new RepayEntity();
//        mMap.setPayMethod(mPayParams.getPayMethod())
//                .setMemberId(mPayParams.getMemberId())
//                .setPlatform(mPayParams.getPlatform())
//                .setOpenTime(mPayParams.getOpenTime());
//        Call<TempEntity> call = service.postPrePayInfo(mPayParams.getToken(), mMap);
//        call.enqueue(new Callback<TempEntity>() {
//            @Override
//            public void onResponse(Call<TempEntity> call, Response<TempEntity> response) {
//                if (response.isSuccessful()) {
//                    TempEntity mBody = response.body();
//                    TLog.error("server give me some body:"+new Gson().toJson(mBody));
//                    if (mBody != null) {
//                        if (mBody.getCode() == 200) {
//                            if (mMap.getPayMethod().equalsIgnoreCase("zfb")) {
////                            TODO    AlI
//                                c.onSuccess(response.body().getZfb());
//                            } else {
////                                TODO WX
//                                c.onSuccess(new Gson().toJson(response.body().getWx()));
//                            }
//                        }else{
//                            c.onFailure();
//                        }
//                    }
//                } else {
//                    c.onFailure();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<TempEntity> call, Throwable t) {
//                c.onFailure();
//            }
//        });
    }
}
