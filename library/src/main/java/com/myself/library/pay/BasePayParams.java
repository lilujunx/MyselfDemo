package com.myself.library.pay;

import android.app.Activity;

import com.myself.library.pay.enums.HttpType;
import com.myself.library.pay.enums.NetworkClientType;
import com.myself.library.pay.enums.PayWay;

/**
 * 根据需要自定义支付实体
 */


public class BasePayParams {

    private Activity mActivity;
    private String mWechatAppID;
    private PayWay mPayWay;
    private HttpType mHttpType = HttpType.Post;
    private NetworkClientType mNetworkClientType = NetworkClientType.Retrofit;
    private String mApiUrl;


    public Activity getActivity() {
        return mActivity;
    }

    public void setActivity(Activity mActivity) {
        this.mActivity = mActivity;
    }

    public String getWechatAppID() {
        return mWechatAppID;
    }

    public void setWechatAppID(String mWechatAppID) {
        this.mWechatAppID = mWechatAppID;
    }

    public PayWay getPayWay() {
        return mPayWay;
    }

    public void setPayWay(PayWay mPayWay) {
        this.mPayWay = mPayWay;
    }

    public HttpType getHttpType() {
        return mHttpType;
    }

    public void setHttpType(HttpType mHttpType) {
        this.mHttpType = mHttpType;
    }

    public NetworkClientType getNetworkClientType() {
        return mNetworkClientType;
    }

    public void setNetworkClientType(NetworkClientType mNetworkClientType) {
        this.mNetworkClientType = mNetworkClientType;
    }

    public String getApiUrl() {
        return mApiUrl;
    }

    public void setApiUrl(String mApiUrl) {
        this.mApiUrl = mApiUrl;
    }

    public static class Builder {
        protected Activity mActivity;
        protected String wechatAppId;
        protected PayWay payWay;
        protected HttpType httpType;
        protected NetworkClientType mNetworkClientType;
        protected String apiUrl;

        public Builder(Activity activity) {
            mActivity = activity;
        }

        public NetworkClientType getNetworkClientType() {
            return mNetworkClientType;
        }

        public void setNetworkClientType(NetworkClientType mNetworkClientType) {
            this.mNetworkClientType = mNetworkClientType;
        }

        public Activity getActivity() {
            return mActivity;
        }

        public void setActivity(Activity mActivity) {
            this.mActivity = mActivity;
        }

        public String getWechatAppId() {
            return wechatAppId;
        }

        public void setWechatAppId(String mWechatAppId) {
            wechatAppId = mWechatAppId;
        }

        public PayWay getPayWay() {
            return payWay;
        }

        public void setPayWay(PayWay mPayWay) {
            payWay = mPayWay;
        }

        public HttpType getHttpType() {
            return httpType;
        }

        public void setHttpType(HttpType mHttpType) {
            httpType = mHttpType;
        }

        public String getApiUrl() {
            return apiUrl;
        }

        public void setApiUrl(String mApiUrl) {
            apiUrl = mApiUrl;
        }

        public BasePayParams build() {
            BasePayParams params = new BasePayParams();
            params.setActivity(mActivity);
            params.setWechatAppID(wechatAppId);
            params.setPayWay(payWay);
            params.setHttpType(httpType);
            params.setNetworkClientType(mNetworkClientType);
            params.setApiUrl(apiUrl);
            return params;
        }

    }
}
