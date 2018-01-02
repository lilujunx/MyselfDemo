package com.jiayou.myselfdemo.ui.listener;

import android.widget.Toast;

import com.google.gson.Gson;
import com.jiayou.myselfdemo.Constant;
import com.jiayou.myselfdemo.utils.TLog;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.myself.library.base.BaseApp.mContextGlobal;


/**
 * 网络访问，回调
 */

public class MyCallBack implements Callback {
    private MyCallBackResult mMyCallBackResult;
    private Class mClass;
    private String logStr;
    private int tag;

    /**
     * 无参构造器
     **/
    public MyCallBack(int mTag) {
        this.tag = mTag;
//        try {
//            mClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
//        } catch (Exception e) {
//
//        }
    }

    /**
     * 一参构造器
     *
     * @param mlogStr 若需要打印日志，则传值，不打印日志则调用无参构造器
     */

    public MyCallBack(int mTag, String mlogStr) {
        this(mTag);
        this.logStr = mlogStr;
    }


    public void setMyCallBackResult(MyCallBackResult mMyCallBackResult) {
        this.mMyCallBackResult = mMyCallBackResult;
    }

    @Override
    public void onResponse(Call call, Response response) {

        if (logStr != null) {
            TLog.error(logStr + "的response:" + response.toString());
        }
        try {
            int code = response.code();
            String message = response.message();
            if (code == 404) {
                if (Constant.isDebug) {
                    Toast.makeText(mContextGlobal, "404 not found ", Toast.LENGTH_SHORT).show();
                }
            } else if (code == 200) {
                JSONObject jsonObjectBody = new JSONObject((String) response.body());
                int codeBody = jsonObjectBody.getInt("code");
                if (logStr != null) {
                    TLog.error(logStr + "body:" + response.body());
                }
                if (codeBody == Constant.SUCCESS) {
                    //访问成功

                    if (mMyCallBackResult != null) {
                        mMyCallBackResult.onSuccess((String) response.body(), tag, new Gson());
                    }
                } else if (codeBody == Constant.TOKEN_OVER) {
//                您的账号在别的地方登录
//                  todo   SharedPrefrencesUtil

                    if (mMyCallBackResult != null) {
                        mMyCallBackResult.onTokenOver(tag);
                    }
                } else {
                    String msg = jsonObjectBody.getString("msg");
                    Toast.makeText(mContextGlobal, msg, Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(mContextGlobal, code + "," + message, Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException mE) {
            if (logStr != null) {
                TLog.error(logStr + "的response,json解析异常：" + mE.toString());
            }
            mE.printStackTrace();
        }
    }

    @Override
    public void onFailure(Call call, Throwable t) {
        Toast.makeText(mContextGlobal, "请检查您的网络", Toast.LENGTH_SHORT).show();
        if (mMyCallBackResult != null) {
            mMyCallBackResult.onFail(t, tag);
        }
    }


    public interface MyCallBackResult {
        /**
         * 已做处理，此时的response 就已经是解析完的实体类了
         **/
        void onSuccess(String body, int tag, Gson mGson);

        /***
         * 失败的时候要干的事情，比如隐藏某控件什么的
         */
        void onFail(Throwable t, int tag);

        /***
         * token过期要干的事情，已经重置isLogin
         */
        void onTokenOver(int tag);

    }
}
