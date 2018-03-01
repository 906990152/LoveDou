package com.sgl.dou.http.base;

import retrofit2.http.POST;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by sugl on 2018/3/1 0001.
 */

public interface BaseGraphqlService {
    @POST()
    Observable<String> getResult(@Url String url);
}
