package com.sgl.dou.presenter.base;

import com.sgl.dou.R;

/**
 * Created by sugl on 2018/3/1 0001.
 */

public class BaseFragActDelegate extends HeaderDelegate {
    @Override
    public int getRootLayoutId() {
        return R.layout.activity_base;
    }

    @Override
    public void initWidget() {
        setShowTitle(false);
        super.initWidget();
    }
}
