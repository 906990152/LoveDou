package com.sgl.dou.presenter.base;

import com.sgl.dou.R;
import com.sgl.dou.mvp.View.AppDelegate;
import com.sgl.dou.utils.NetworkUtils;
import com.sgl.dou.utils.ToastUtil;

/**
 * Created by Administrator on 2018/3/1 0001.
 */

public abstract class HeaderDelegate extends AppDelegate {
    private boolean showTitle = true;

    // 如果不需要默认的头像，设置false 注：要在 super.initWidget();之前设置才有效
    public void setShowTitle(boolean showTitle) {
        this.showTitle = showTitle;
    }

    public void finish() {
        getActivity().finish();
    }

    // 是否有网络
    public boolean havaNet() {
        if (!NetworkUtils.isConnected()) {
            ToastUtil.showErrorToast(R.string.net_error);
            return false;
        }
        return true;

    }
}
