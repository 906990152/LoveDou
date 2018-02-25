package com.sgl.dou.presenter;

import com.sgl.dou.constract.LoginConstract;

/**
 * Created by Sola on 2017/12/24.
 */

public class LoginPresenter implements LoginConstract.Presenter<LoginConstract.View> {
    LoginConstract.View view;
    @Override
    public void attachView(LoginConstract.View view) {
        this.view=view;
    }

    @Override
    public void detachView() {
      view=null;
    }

    @Override
    public void login() {
        view.logingSucces();

    }
}

