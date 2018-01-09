package com.myself.library.pay;

/**
 * 支付，回调
 */

public interface NetworkClientInterf {
    interface CallBack<R> {
        void onSuccess(R result);

        void onFailure();
    }

    void get(BasePayParams payParams, CallBack c);

    void post(BasePayParams payParams, CallBack c);
}
