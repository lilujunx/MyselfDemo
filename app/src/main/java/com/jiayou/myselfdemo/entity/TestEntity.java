package com.jiayou.myselfdemo.entity;

/**
 * Created by Malik J on 2017/12/18.
 */

public class TestEntity {
    private String title;
    private String content = "";
    private boolean isCheck=false;


    @Override
    public String toString() {
        return "TestEntity{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", isCheck=" + isCheck +
                '}';
    }

    public boolean isCheck() {
        return isCheck;
    }

    public TestEntity setCheck(boolean mCheck) {
        isCheck = mCheck;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public TestEntity setTitle(String mTitle) {
        title = mTitle;
        return this;
    }

    public String getContent() {
        return content;
    }

    public TestEntity setContent(String mContent) {
        content = mContent;
        return this;
    }
}
