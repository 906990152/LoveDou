package com.sgl.dou.rxretrofitlibrary.http;

import com.ihsanbal.logging.Level;
import com.ihsanbal.logging.LoggingInterceptor;
import com.sgl.dou.rxretrofitlibrary.Api.BaseApi;
import com.sgl.dou.rxretrofitlibrary.RxRetrofitApp;
import com.sgl.dou.rxretrofitlibrary.exception.FactoryException;
import com.sgl.dou.rxretrofitlibrary.exception.RetryWhenNetworkException;
import com.sgl.dou.rxretrofitlibrary.http.cookie.CacheInterceptor;
import com.sgl.dou.rxretrofitlibrary.subscribers.ProgressSubscriber;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.internal.platform.Platform;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * http交互处理类
 */
public class HttpManager {
    private String baseUrl;
    private OkHttpClient httpClient;
    private Retrofit retrofit;
    /*超时时间-默认6秒*/
    private int connectionTime = 6;
    private static HttpManager instance = null;
    private static HttpManager videoInstance = null;
    private static HttpManager gameUserInstance = null;
    private HttpManager(String baseUrl) {
        this.baseUrl = baseUrl;

        int cacheSize = 200 * 1024 * 1024;
        File cacheDirectory = new File(RxRetrofitApp.getApplication().getCacheDir(), "httpcache");
        Cache cache = new Cache(cacheDirectory, cacheSize);
        httpClient = new OkHttpClient.Builder().cache(cache)
                .addInterceptor(new CacheInterceptor()) // 网络缓存
                .addNetworkInterceptor(new CacheInterceptor())
                .addInterceptor(new LoggingInterceptor.Builder()
                        .loggable(RxRetrofitApp.isDebug)
                        .setLevel(Level.BASIC)
                        .log(Platform.INFO)
                        .request("Request")
                        .response("Response")
                        .addHeader("version", "1.0")
                        .build())
                .connectTimeout(connectionTime, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .build();

        /*创建retrofit对象*/
        retrofit = new Retrofit.Builder()
                .client(httpClient)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(baseUrl)
                .build();
        retrofit.newBuilder().baseUrl(baseUrl);
    }

    public synchronized static HttpManager getInstance(String baseUrl) {
        if (instance == null) {
            instance = new HttpManager(baseUrl);
        }
        return instance;
    }

    public synchronized static HttpManager getVideoInstance(String baseUrl) {
        if (videoInstance == null) {
            videoInstance = new HttpManager(baseUrl);
        }
        return videoInstance;
    }

    public synchronized static HttpManager getGameUserInstance(String baseUrl) {
        if (gameUserInstance == null) {
            gameUserInstance = new HttpManager(baseUrl);
        }
        return gameUserInstance;
    }
    /**
     * 处理http请求
     *
     * @param basePar 封装的请求数据
     */
    public void doHttpDeal(BaseApi basePar) {
        /*rx处理*/
        ProgressSubscriber subscriber = new ProgressSubscriber(basePar);
        Observable observable = basePar.getObservable(retrofit)
                /*失败后的retry配置*/
                .retryWhen(new RetryWhenNetworkException())
                /*异常处理*/
                .onErrorResumeNext(funcException)
                /*生命周期管理*/
//                .compose(((RxAppCompatActivity)appCompatActivity).bindToLifecycle())
                //Note:手动设置在activity onDestroy的时候取消订阅
//                .compose(((RxAppCompatActivity)appCompatActivity).bindUntilEvent(ActivityEvent.DESTROY))
                /*http请求线程*/
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                /*回调线程*/
                .observeOn(AndroidSchedulers.mainThread());

        /*数据回调*/
        observable.subscribe(subscriber);
    }


    /**
     * 异常处理
     */
    Func1 funcException = new Func1<Throwable, Observable>() {
        @Override
        public Observable call(Throwable throwable) {
            return Observable.error(FactoryException.analysisExcetpion(throwable));
        }
    };

}
