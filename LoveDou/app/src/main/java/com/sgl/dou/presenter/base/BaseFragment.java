package com.sgl.dou.presenter.base;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.sgl.dou.mvp.View.AppDelegate;
import com.sgl.dou.mvp.presenter.FragmentPresenter;

/**
 * Created by sugl on 2018/2/27 0027.
 */

public abstract class BaseFragment<T extends AppDelegate> extends FragmentPresenter<T> implements View.OnClickListener {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        registeredEventBus();
        getIntentData();
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unRegister();
    }

    // 注册eventbus
    protected void registeredEventBus() {

    }

    //解除eventbus
    private void unRegister() {

    }

    protected void getIntentData() {

        if (getArguments() == null) {
            return;
        }
    }
}
