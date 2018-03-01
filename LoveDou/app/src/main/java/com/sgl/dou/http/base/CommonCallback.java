package com.sgl.dou.http.base;

/**
 * Created by sgl on 2018/3/1 0001.
 *  * 通用回调
 * 解析数据，去掉{"data":{}}这层数据
 */

public interface CommonCallback<T> {
    void onSuccess(T result);
    void onError(String error);
}
