package com.jiayou.myselfdemo.pay;


import com.myself.library.pay.NetworkClientInterf;
import com.myself.library.pay.enums.NetworkClientType;

/**
 * Description: 网络访问接口简单工厂.
 */

public class NetworkClientFactory {

    public static NetworkClientInterf newClient(NetworkClientType networkClientType) {
        switch (networkClientType) {
//            case HttpUrlConnetion:
//                return new HttpUrlConnectionClient();
//            case Volley:
//                return new VolleyClient();
            case Retrofit:
                return new RetrofitClient();
//            case OkHttp:
//                return new OkHttpClientImpl();
//            default:
//                return new HttpUrlConnectionClient();
            default:
                return  null;
        }
    }
}
