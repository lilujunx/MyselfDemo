package com.jiayou.myselfdemo.entity;

import com.google.gson.annotations.SerializedName;

/**
 *
 */

public class TempEntity {

    /**
     * code : 200
     * zfb : alipay_sdk=alipay-sdk-java-dynamicVersionNo&app_id=2017112800222932&biz_content=%7B%22out_trade_no%22%3A%221512185841%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22subject%22%3A%22UGameBox%22%2C%22total_amount%22%3A%221%22%7D&charset=UTF-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2F120.24.153.179%3A8080%2Fmirrorspace%2Fzfbcallback&sign=BrFQwLp2hfIbA5KF22mGa1r1wPOQKpEgLbUjTNKartvXIJ%2F5Rm%2FeIcelUvDWmWjd3PAyTcNHz6I9lXcRQENr3iW%2BjsCbPV2zs%2FwK3F4ni%2F2cZOVo3UXlNQ2CsJPO1AKzFdvXBzSijtfb3vNkct6xC1qcCh93WF1eDTl90mOdk91hIPbSGChS6fIHYCq0oR2MfyuzU14XjX%2FitiJ7KbCxUCHSlyYM1YlaTrPaHruJJScYEcEvKxSECAv%2FKG1%2BKcqNEdY%2Bl5JPmjKqQxUzUeJCpH6LZf4q0DUY124UHbopWkeu7h%2BGiEpwZhkFFy6tCVVHOtW3yYsKkY4HhEow9KjZuQ%3D%3D&sign_type=RSA2&timestamp=2017-12-02+11%3A37%3A21&version=1.0
     */

    private int code;
    private String zfb;
    private WxBean wx;
    private String msg;


    @Override
    public String toString() {
        return "TempEntity{" +
                "code=" + code +
                ", zfb='" + zfb + '\'' +
                ", wx=" + wx +
                ", msg='" + msg + '\'' +
                '}';
    }

    public String getMsg() {
        return msg;
    }

    public TempEntity setMsg(String mMsg) {
        msg = mMsg;
        return this;
    }

    public WxBean getWx() {
        return wx;
    }

    public TempEntity setWx(WxBean mWx) {
        wx = mWx;
        return this;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getZfb() {
        return zfb;
    }

    public void setZfb(String zfb) {
        this.zfb = zfb;
    }


    public static class WxBean {
            /**
             * sign : BA693C38FB258EC039531B46FD105229
             * timestamp : 1512367708
             * noncestr : dgre37njketdirmkp0evznwp922blf9f
             * partnerid : 1493172272
             * prepayid : wx201712041408282601e6149f0591099599
             * package : Sign=WXPay
             * appid : wx5b5c92f0d18d2237
             */

            private String sign;
            private String timestamp;
            private String noncestr;
            private String partnerid;
            private String prepayid;
            @SerializedName("package")
            private String packageX;
            private String appid;


            @Override
            public String toString() {
                return "WxBean{" +
                        "sign='" + sign + '\'' +
                        ", timestamp='" + timestamp + '\'' +
                        ", noncestr='" + noncestr + '\'' +
                        ", partnerid='" + partnerid + '\'' +
                        ", prepayid='" + prepayid + '\'' +
                        ", packageX='" + packageX + '\'' +
                        ", appid='" + appid + '\'' +
                        '}';
            }

            public String getSign() {
                return sign;
            }

            public void setSign(String sign) {
                this.sign = sign;
            }

            public String getTimestamp() {
                return timestamp;
            }

            public void setTimestamp(String timestamp) {
                this.timestamp = timestamp;
            }

            public String getNoncestr() {
                return noncestr;
            }

            public void setNoncestr(String noncestr) {
                this.noncestr = noncestr;
            }

            public String getPartnerid() {
                return partnerid;
            }

            public void setPartnerid(String partnerid) {
                this.partnerid = partnerid;
            }

            public String getPrepayid() {
                return prepayid;
            }

            public void setPrepayid(String prepayid) {
                this.prepayid = prepayid;
            }

            public String getPackageX() {
                return packageX;
            }

            public void setPackageX(String packageX) {
                this.packageX = packageX;
            }

            public String getAppid() {
                return appid;
            }

            public void setAppid(String appid) {
                this.appid = appid;
            }
        }

}
