package com.sgl.dou.constract;

import com.sgl.dou.mvp.base.BaseContract;

/**
 * Created by Sola on 2017/12/24.
 */

public interface LoginConstract {
    interface Presenter<T> extends BaseContract.BasePresenter<T> {
        void login();
    }

    interface View extends BaseContract.BaseView {
        void logingSucces();
    }
}
