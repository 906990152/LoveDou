package com.sgl.dou.mvp.base;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sgl.dou.mvp.View.AppDelegate;

/**
 * Created by Sola on 2017/12/23.
 * fragment 基类，含有懒加载
 */

public abstract class BaseFragment<V extends AppDelegate> extends Fragment {
    protected V viewDelegate;

    protected boolean isLazyLoaded = false;
    protected boolean isPrepared = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("sgl1", "oncreate-----");
        try {
            viewDelegate = getDelegateClass().newInstance();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        Log.e("sgl1", "onCreateView-----");
        viewDelegate.create(inflater, container, savedInstanceState);
        return viewDelegate.getRootView();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.e("sgl1", "onViewCreated-----");
        isPrepared = true;
        viewDelegate.initWidget();
        viewDelegate.initValue();
        initView();
        initValue();
        lazyLoad();
        bindEventListener();
    }

    protected void initView() {
    }

    protected void initValue() {

    }

    //调用懒加载
    private void lazyLoad() {
        if (getUserVisibleHint() && isPrepared && !isLazyLoaded) {
            onLazyLoad();
            isLazyLoaded = true;
        }else {
            return;
        }

    }

    protected void onLazyLoad() {

    }

    //监听事件
    protected void bindEventListener() {

    }

    //视图是否可见
    //setUserVisbleHint先于onCreateView执行
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.e("sgl1", "visibleHint-----");
        lazyLoad();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        viewDelegate = null;
    }

    protected abstract Class<V> getDelegateClass();
}
