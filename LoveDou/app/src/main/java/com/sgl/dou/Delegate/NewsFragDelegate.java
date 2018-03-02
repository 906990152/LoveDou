package com.sgl.dou.delegate;

import com.sgl.dou.R;
import com.sgl.dou.presenter.base.RecyclerRefreshDelegate;

/**
 * Created by Administrator on 2018/3/2 0002.
 */

public class NewsFragDelegate extends RecyclerRefreshDelegate {
    @Override
    public int getRootLayoutId() {
        return R.layout.fragment_news;
    }

    @Override
    public int getRefreshViewId() {
        return R.id.refresh_view;
    }

    @Override
    protected int getRecyclerViewId() {
        return R.id.recycler_view;
    }
}
