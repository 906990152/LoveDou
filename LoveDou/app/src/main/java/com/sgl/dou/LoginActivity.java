package com.sgl.dou;

import com.sgl.dou.Delegate.BlankFragDelegate;
import com.sgl.dou.constract.LoginConstract;
import com.sgl.dou.mvp.base.BaseAcitvity;
import com.sgl.dou.presenter.LoginPresenter;

/**
 * Created by Sola on 2017/12/24.
 */

public class LoginActivity extends BaseAcitvity<BlankFragDelegate>implements LoginConstract.View {
    @Override
    protected Class<BlankFragDelegate> getDelegateClass() {
        return null;
    }

    @Override
    protected void initView() {
        super.initView();
        loginPresenter.attachView(this);
    }

    private LoginPresenter loginPresenter;


    @Override
    public void showError() {
    }

    @Override
    public void success() {

    }

    @Override
    public void logingSucces() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginPresenter.detachView();
    }
}
