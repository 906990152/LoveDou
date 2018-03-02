package com.sgl.dou.presenter.fragment;

import com.sgl.dou.delegate.NewsFragDelegate;
import com.sgl.dou.library.recycleview.adapter.MultiItemTypeAdapter;
import com.sgl.dou.model.entry.NewsEntry;
import com.sgl.dou.model.manager.NewsManager;
import com.sgl.dou.presenter.base.PullRefreshFragment;

/**
 * Created by Administrator on 2018/3/2 0002.
 */

public class NewsFragment extends PullRefreshFragment<NewsEntry, NewsFragDelegate> {
    @Override
    protected Class<NewsFragDelegate> getDelegateClass() {
        return NewsFragDelegate.class;
    }

    @Override
    protected MultiItemTypeAdapter getAdapter() {
        return null;
    }

    @Override
    protected void getData() {
        NewsManager.getInstance().getNewsList(offset, limit, 0, this);
    }
}
