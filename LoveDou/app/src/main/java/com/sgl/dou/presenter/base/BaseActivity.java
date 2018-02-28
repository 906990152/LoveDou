package com.sgl.dou.presenter.base;

import android.content.pm.ActivityInfo;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.View;

import com.sgl.dou.mvp.View.AppDelegate;
import com.sgl.dou.mvp.presenter.ActivityPresenter;

/**
 * Created by Administrator on 2018/2/27 0027.
 */

public abstract class BaseActivity<T extends AppDelegate> extends ActivityPresenter<T> implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //禁止横屏
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initValue() {
        getIntentData();
        super.initValue();
    }

    //获取acitivity传递过来的值
    protected void getIntentData() {
        if (getIntent() == null) {
            return;
        }
    }

    @Override
    public void onClick(View v) {

    }
}
