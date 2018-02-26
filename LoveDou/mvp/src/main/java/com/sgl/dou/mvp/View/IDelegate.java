package com.sgl.dou.mvp.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Sola on 2017/12/23.
 * 视图层代理的接口协议
 */

public interface IDelegate {
    void create(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    View getRootView();//得到根布局

    void initWidget();//初始化控件
    void initValue();//初始化控件设置值
}
