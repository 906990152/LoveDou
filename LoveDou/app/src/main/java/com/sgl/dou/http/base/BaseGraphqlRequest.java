package com.sgl.dou.http.base;

import android.widget.Toast;

import com.google.gson.Gson;
import com.sgl.dou.base.AppConfig;
import com.sgl.dou.base.DouApp;
import com.sgl.dou.model.CommonEntity;
import com.sgl.dou.rxretrofitlibrary.Api.BaseApi;
import com.sgl.dou.rxretrofitlibrary.RxRetrofitApp;
import com.sgl.dou.rxretrofitlibrary.exception.ApiException;
import com.sgl.dou.rxretrofitlibrary.http.HttpManager;
import com.sgl.dou.rxretrofitlibrary.listener.HttpOnNextListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import retrofit2.Retrofit;
import rx.Observable;

/**
 * Created by Administrator on 2018/3/1 0001.
 */

public class BaseGraphqlRequest<T> extends BaseApi implements HttpOnNextListener {
    private String urlParam="";
    private CommonCallback commonCallback;
    private Class<T> tClass;
    public BaseGraphqlRequest(){
        setOnNextListener(this);
    }

    @Override
    public Observable getObservable(Retrofit retrofit) {
        BaseGraphqlService graphqlService=retrofit.create(BaseGraphqlService.class);
        graphqlService.getResult(GenURL(urlParam));
        return null;
    }
    protected  String GenURL(String param) {
        try {
            String paramEncode = URLEncoder.encode(param, "UTF-8");
            return ("graphql?query=" + paramEncode);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return ("graphql?query=" + param);
        }
    }
    @Override
    public void onNext(String resulte, String mothead) {
        CommonEntity commonEntity = new CommonEntity();

//        数据格式为{status:0,data:{},msg:} 对象
//        data=new Gson().fromJson(resulte,tClass);
        try {
            T data=null;
            // 数据格式为{status:0,data:{user:[{},{}]},msg:}
            JSONObject jsonObject=new JSONObject(resulte);
            String jsonStr=jsonObject.getJSONObject("data").toString();
            data=new Gson().fromJson(jsonStr,tClass);
            commonEntity.setData(data);
        } catch (JSONException e) {
            Toast.makeText(DouApp.context,"json解析错误",Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        if (commonCallback!=null){
            commonCallback.onSuccess(commonEntity);
        }

    }

    @Override
    public void onError(ApiException e) {
      if (commonCallback!=null){
          if (RxRetrofitApp.isDebug){
              commonCallback.onError(e.getDisplayMessage());
          }else {
              commonCallback.onError("");
          }
      }
    }
    public void setCommonCallback(String urlParam,CommonCallback commonCallback,Class<T> tClass){
        this.urlParam=urlParam;
        this.commonCallback=commonCallback;
        this.tClass=tClass;
        HttpManager.getInstance(AppConfig.GRAPHQL_ADDRESS).doHttpDeal(this);
    }
}
