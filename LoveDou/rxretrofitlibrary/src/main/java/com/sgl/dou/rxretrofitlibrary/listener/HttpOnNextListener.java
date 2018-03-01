package com.sgl.dou.rxretrofitlibrary.listener;


import com.sgl.dou.rxretrofitlibrary.exception.ApiException;

import org.json.JSONException;

/**
 * 成功回调处理
 */
public interface HttpOnNextListener {
    /**
     * 成功后回调方法
     * @param resulte
     * @param mothead
     */
  void onNext(String resulte, String mothead) throws JSONException;

    /**
     * 失败
     * 失败或者错误方法
     * 自定义异常处理
     * @param e
     */
  void onError(ApiException e);
}
