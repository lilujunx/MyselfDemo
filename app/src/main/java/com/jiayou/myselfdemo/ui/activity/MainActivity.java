package com.jiayou.myselfdemo.ui.activity;

import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.jiayou.myselfdemo.R;
import com.jiayou.myselfdemo.base.LoadingActivity;
import com.jiayou.myselfdemo.pay.NetworkClientFactory;
import com.jiayou.myselfdemo.pay.PayParams;
import com.jiayou.myselfdemo.utils.TLog;
import com.myself.library.listener.OnPayInfoRequestListener;
import com.myself.library.listener.OnPayResultListener;
import com.myself.library.pay.EasyPay;
import com.myself.library.pay.enums.PayWay;

public class MainActivity extends LoadingActivity {


    @Override
    public int initRootLayout() {
        return R.layout.main;
    }


    @Override
    public boolean isUseTitleBar() {
        return true;
    }

    @Override
    public void initDatas() {

    }


    @Override
    public void initView() {
        setTitleCenter("dsadawdwa");
    }

    @Override
    public void onReload() {
        Toast.makeText(mActivitySelf, "xxxxx", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                onReloadSucess();
            }
        }, 2000);
    }


    @Override
    public void initOthers() {

    }

    public void doNet(View mView) {
//        noIntenet(TDevice.hasInternet());
        PayParams.Builder mBuilder = new PayParams.Builder(mActivitySelf)
                .setMemberId(3)
                .setOpenTime(3)
                .setPayMethod("zfb")
                .setPlatform("and")
                .setToken("6897f313-c987-4006-8872-b2340ccfc08c");
        mBuilder.setPayWay(PayWay.ALiPay);
        PayParams mBuild = mBuilder.build();
        EasyPay.newInstance(mBuild).requestPayInfo(NetworkClientFactory.newClient(mBuild.getNetworkClientType()), new OnPayInfoRequestListener() {
            @Override
            public void onPayInfoRequetStart() {
                TLog.error("开始链接");
            }

            @Override
            public void onPayInfoRequstSuccess() {
                TLog.error("链接成功");
            }

            @Override
            public void onPayInfoRequestFailure() {
                TLog.error("链接失败");
            }
        }).toPay(new OnPayResultListener() {
            @Override
            public void onPaySuccess(PayWay payWay) {
                Toast.makeText(mActivitySelf, "支付成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPayCancel(PayWay payWay) {
                Toast.makeText(mActivitySelf, "支付取消", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPayFailure(PayWay payWay, int errCode) {
                Toast.makeText(mActivitySelf, "支付失败，错误码为:" + errCode, Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void doNet1(View mView) {
//        noIntenet(TDevice.hasInternet());
    }
}
