package com.sgl.dou.mvp.base;

/**
 * Created by Sola on 2017/12/24.
 */

public interface BaseContract {
    interface BasePresenter<T>{
        void attachView(T view);
        void detachView();
    }
    interface BaseView{
        void showError();
        void success();
    }
}
