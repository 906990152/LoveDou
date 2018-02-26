package com.sgl.dou.mvp.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.sgl.dou.mvp.View.AppDelegate;

/**
 * Created by Sola on 2017/12/23.
 */

public abstract class BaseAcitvity<T extends AppDelegate> extends AppCompatActivity {
    protected T viewDelegate;

    public BaseAcitvity() {
        try {
            viewDelegate = getDelegateClass().newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException("create IDelegate error");
        } catch (IllegalAccessException e) {
            throw new RuntimeException("create IDelegate error");
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewDelegate.create(getLayoutInflater(), null, savedInstanceState);
        setContentView(viewDelegate.getRootView());
        viewDelegate.initWidget();//控件初始化
        viewDelegate.initValue();//给控件赋值
        initView();
        initValue();
        getData();
        bindEventListener();
    }

    protected void initView() {
    }

    protected void initValue() {

    }

    protected void getData() {

    }

    //监听事件
    protected void bindEventListener() {

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (viewDelegate == null) {
            try {
                viewDelegate = getDelegateClass().newInstance();
            } catch (InstantiationException e) {
                throw new RuntimeException("create IDelegate error");
            } catch (IllegalAccessException e) {
                throw new RuntimeException("create IDelegate error");
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewDelegate=null;

    }

    protected abstract Class<T> getDelegateClass();
}
