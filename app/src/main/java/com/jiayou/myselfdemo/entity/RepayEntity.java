package com.jiayou.myselfdemo.entity;

/**
 * Created by Malik J on 2017/12/2.
 */

public class RepayEntity {
//    用户开通会员;platform=zfb或wx;memberId=会员id；openTime=开通时间，传1表示一个季度
    private String platform ;
    private String payMethod ;
    private int memberId ;
    private int openTime ;

    public String getPayMethod() {
        return payMethod;
    }

    public RepayEntity setPayMethod(String mPayMethod) {
        payMethod = mPayMethod;
        return this;
    }

    @Override
    public String toString() {
        return "RepayEntity{" +
                "platform='" + platform + '\'' +
                ", payMethod='" + payMethod + '\'' +
                ", memberId=" + memberId +
                ", openTime=" + openTime +
                '}';
    }

    public String getPlatform() {
        return platform;
    }

    public RepayEntity setPlatform(String mPlatform) {
        platform = mPlatform;
        return this;
    }

    public int getMemberId() {
        return memberId;
    }

    public RepayEntity setMemberId(int mMemberId) {
        memberId = mMemberId;
        return this;
    }

    public int getOpenTime() {
        return openTime;
    }

    public RepayEntity setOpenTime(int mOpenTime) {
        openTime = mOpenTime;
        return this;
    }
}
