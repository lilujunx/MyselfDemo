package com.myself.library.pay;

import com.google.gson.annotations.SerializedName;

/**
 *   微信预支付返回字符串解析
 */

public class PrePayInfo {
//    {"wx":{
// "sign":"BA693C38FB258EC039531B46FD105229",
// "timestamp":"1512367708",
// "noncestr":"dgre37njketdirmkp0evznwp922blf9f",
// "partnerid":"1493172272",
// "prepayid":"wx201712041408282601e6149f0591099599",
// "package":"Sign=WXPay",
// "appid":"wx5b5c92f0d18d2237"}
// ,"code":200}

    @SerializedName("appid")
    public String appId;
    @SerializedName("partnerid")
    public String partnerid;
    @SerializedName("prepayid")
    public String prepayid;
    @SerializedName("package")
    public String packageValue;
    @SerializedName("noncestr")
    public String nonceStr;
    @SerializedName("timestamp")
    public String timeStamp;
    @SerializedName("sign")
    public String signType;


    @Override
    public String toString() {
        return "PrePayInfo{" +
                "appId='" + appId + '\'' +
                ", partnerid='" + partnerid + '\'' +
                ", prepayid='" + prepayid + '\'' +
                ", packageValue='" + packageValue + '\'' +
                ", nonceStr='" + nonceStr + '\'' +
                ", timeStamp='" + timeStamp + '\'' +
                ", signType='" + signType + '\'' +
                '}';
    }

    public String getAppid() {
        return appId;
    }

    public void setAppid(String appId) {
        this.appId = appId;
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

    public String getPackageValue() {
        return packageValue;
    }

    public void setPackageValue(String packageValue) {
        this.packageValue = packageValue;
    }

    public String getNoncestr() {
        return nonceStr;
    }

    public void setNoncestr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getTimestamp() {
        return timeStamp;
    }

    public void setTimestamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getSign() {
        return signType;
    }

    public void setSign(String signType) {
        this.signType = signType;
    }
}
