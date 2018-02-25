package com.sgl.dou.rxretrofitlibrary.net.cookie;

import com.sgl.dou.rxretrofitlibrary.RxRetrofitApp;
import com.sgl.dou.rxretrofitlibrary.utils.NetworkUtil;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 缓存方式拦截器
 * Created by Sola on 2018/1/4.
 */

public class CacheInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        if (!NetworkUtil.isConnected(RxRetrofitApp.getApplication())) {//没网强制从缓存读取(必须得写，不然断网状态下，退出应用，或者等待一分钟后，就获取不到缓存）
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();
        }
        Response response = chain.proceed(request);
        Response responseLatest;
        if (NetworkUtil.isConnected(RxRetrofitApp.getApplication())) {
            int maxAge = 60; //有网失效一分钟
            responseLatest = response.newBuilder()
                    .removeHeader("Pragma")// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                    .removeHeader("Cache-Control")
                    .header("Cache-Control", "public, max-age=" + maxAge)
                    .build();
        } else {
            int maxStale = 60 * 60 * 6; // 没网失效6小时
            responseLatest= response.newBuilder()
                    .removeHeader("Pragma")
                    .removeHeader("Cache-Control")
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                    .build();
        }
        return  responseLatest;
    }

}
