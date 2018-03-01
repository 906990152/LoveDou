package com.sgl.dou.presenter.base;

import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.sgl.dou.mvp.View.AppDelegate;

/**
 * Created by sugl on 2018/2/27 0027.
 */

public abstract class PullRefreshDelegate extends HeaderDelegate {
    public static final int RECYCLER_BOTH = 0; // 设置刷新加载
    public static final int RECYCLER_DISABLE = 1; //  不刷新不加载
    public static final int RECYCLER_REFRESH = 2; // 只刷新
    public static final int RECYCLER_LOAD = 3; // 只加载
    public SmartRefreshLayout refreshView;
    public int refreshModel = RECYCLER_BOTH;
    public boolean reset;//true 不刷新时直接获取数据
    public boolean havaPull = true; // 是否有用到刷新加载控件

    @Override
    public void initWidget() {
        super.initWidget();
        if (!havaPull) {
            return;
        }
        refreshView = get(getRefreshViewId());

    }
    //设置刷新控件刷新和加载风格
    private void setHeadAndFooterStyle(){
        if (refreshView==null){
            return;
        }
        MaterialHeader materialHeader=new MaterialHeader(this.getActivity());
        refreshView.setRefreshHeader(materialHeader);
        //设置 Footer 为 球脉冲
        refreshView.setRefreshFooter(new ClassicsFooter(this.getActivity()).setDrawableSize(15));
        refreshView.setEnableHeaderTranslationContent(false);
        refreshView.setEnableLoadMoreWhenContentNotFull(false);// 在内容不满一页的时候，是否可以上拉加载更多

    }
    //设置recycle 刷新类型类型
    protected void setRefreshModel(int refreshModel){
        this.refreshModel=refreshModel;
        if(refreshView==null){
            return;
        }
        switch (refreshModel){
            case RECYCLER_BOTH:
                refreshView.setEnableRefresh(true);
                refreshView.setEnableLoadMore(true);
                break;
            case RECYCLER_DISABLE:
                reset=true;
                refreshView.setEnableRefresh(false);
                refreshView.setEnableLoadMore(false);
                refreshView.finishLoadMore();
                refreshView.setNoMoreData(true);
                break;
            case RECYCLER_REFRESH:
                refreshView.setEnableRefresh(true);
                refreshView.setEnableLoadMore(false);
                refreshView.finishLoadMore();
                refreshView.setNoMoreData(true);
                break;
            case RECYCLER_LOAD:
                reset = true;
                refreshView.setEnableRefresh(false);
                refreshView.setEnableLoadMore(true);
                break;
        }
        setFootStyle();

    }
    protected void setFootStyle(){
        refreshView.setEnableAutoLoadMore(false);
        refreshView.setEnableOverScrollDrag(true);
        refreshView.setEnableScrollContentWhenLoaded(true);
    }
    //设置刷新加载监听
    public void setRreshOrLoadListener(OnRefreshListener listener, OnLoadMoreListener loadMoreListener){
        refreshView.setOnRefreshListener(listener);
        refreshView.setOnLoadMoreListener(loadMoreListener);
    }
    //设置自动刷新
    public void setAutoRefresh(){
        refreshView.autoRefresh();
    }
    //是有可以加载更多
    public void setCanLoadMore(boolean more){
        refreshView.setEnableLoadMore(more);
        refreshView.finishLoadMore();
        if (!more){
            refreshView.finishLoadMoreWithNoMoreData();
        }
    }
    //是否可以刷新
    public void setCanRefresh(boolean refresh){
        refreshView.setEnableRefresh(refresh);
    }
    public void setBoth(){
        if (refreshModel==RECYCLER_BOTH||refreshModel==RECYCLER_LOAD){
            refreshView.setEnableLoadMore(true);
        }
        if (refreshModel == RECYCLER_BOTH || refreshModel == RECYCLER_REFRESH) {
            refreshView.setEnableRefresh(true);
        }
        setFootStyle();
        refreshView.setNoMoreData(false);
    }
    //刷新加载完毕
    public void complete(){
        if (refreshView!=null){
            refreshView.finishRefresh();
            refreshView.finishLoadMore();
        }
    }
    public void refresh(){
        if (refreshView==null){
            return;
        }
        if (refreshModel==RECYCLER_BOTH||refreshModel==RECYCLER_LOAD){
            refreshView.setEnableLoadMore(true);
        }
    }
    //获取刷新控件的id
    public abstract int getRefreshViewId();

}
