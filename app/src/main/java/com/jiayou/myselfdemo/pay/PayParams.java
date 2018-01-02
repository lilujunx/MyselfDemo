package com.jiayou.myselfdemo.pay;

import android.app.Activity;

import com.jiayou.myselfdemo.Constant;
import com.myself.library.pay.BasePayParams;
import com.myself.library.pay.enums.HttpType;
import com.myself.library.pay.enums.NetworkClientType;


/**
 * 根据需要自定义支付实体
 */


public class PayParams extends BasePayParams {
    private String token;
    private String platform;
    private int memberId;
    private int openTime;
    private String payMethod;


    public String getToken() {
        return token;
    }

    public String getPlatform() {
        return platform;
    }

    public int getMemberId() {
        return memberId;
    }

    public int getOpenTime() {
        return openTime;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public PayParams setToken(String mToken) {
        token = mToken;
        return this;
    }

    public PayParams setPlatform(String mPlatform) {
        platform = mPlatform;
        return this;
    }

    public PayParams setMemberId(int mMemberId) {
        memberId = mMemberId;
        return this;
    }

    public PayParams setOpenTime(int mOpenTime) {
        openTime = mOpenTime;
        return this;
    }

    public PayParams setPayMethod(String mPayMethod) {
        payMethod = mPayMethod;
        return this;
    }

    public static class Builder extends BasePayParams.Builder {
        private String token;
        private String platform;
        private int memberId;
        private int openTime;
        private String payMethod;


        public Builder(Activity activity) {
            super(activity);
        }


        public Builder setToken(String mToken) {
            token = mToken;
            return this;
        }

        public Builder setPlatform(String mPlatform) {
            platform = mPlatform;
            return this;
        }

        public Builder setMemberId(int mMemberId) {
            memberId = mMemberId;
            return this;
        }

        public Builder setOpenTime(int mOpenTime) {
            openTime = mOpenTime;
            return this;
        }

        public Builder setPayMethod(String mPayMethod) {
            payMethod = mPayMethod;
            return this;
        }

        public PayParams build() {
            PayParams mBuild = build(super.build());
            mBuild.setApiUrl(Constant.BASE_URL);
            mBuild.setHttpType(HttpType.Post);
            mBuild.setMemberId(memberId).setOpenTime(openTime).setPayMethod(payMethod).setPlatform(platform).setToken(token);
            return mBuild;
        }

        private PayParams build(BasePayParams mBasePayParams) {
            PayParams mPayParams = new PayParams();
            mPayParams.setActivity(mBasePayParams.getActivity());
            mPayParams.setWechatAppID(mBasePayParams.getWechatAppID());
            mPayParams.setPayWay(mBasePayParams.getPayWay());
            mPayParams.setHttpType(mBasePayParams.getHttpType());
            mPayParams.setNetworkClientType(mBasePayParams.getNetworkClientType());
            mPayParams.setApiUrl(mBasePayParams.getApiUrl());
            mPayParams.setNetworkClientType(NetworkClientType.Retrofit);
            return mPayParams;
        }
    }

}
