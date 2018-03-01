package com.sgl.dou.http;

import com.sgl.dou.http.base.BaseGraphqlRequest;
import com.sgl.dou.http.base.CommonCallback;

/**
 * Created by sugl on 2018/3/1 0001.
 */

public class HttpRequest extends BaseGraphqlRequest {
    public HttpRequest(){
        super();
    }
    public void postUrl(String url , CommonCallback commonCallback ,Class cls){
        setCommonCallback(url,commonCallback,cls);
    }
}
