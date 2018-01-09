package com.jiayou.myselfdemo.ui.listener;

import com.jiayou.myselfdemo.Constant;

import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * NetService
 */

public interface NetService {

    @GET()
    String doxxx(@Url String url);
}
