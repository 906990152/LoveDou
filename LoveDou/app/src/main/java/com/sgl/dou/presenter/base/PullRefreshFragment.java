package com.sgl.dou.presenter.base;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.sgl.dou.R;
import com.sgl.dou.http.base.CommonCallback;
import com.sgl.dou.library.recycleview.adapter.MultiItemTypeAdapter;
import com.sgl.dou.model.CommonEntity;
import com.sgl.dou.model.ServerStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sugl on 2018/3/1 0001.
 */

public abstract class PullRefreshFragment<T, V extends RecyclerRefreshDelegate> extends BaseFragment<V> implements CommonCallback<CommonEntity<T>> {
    private MultiItemTypeAdapter<T> adapter;
    protected List<T> mList = new ArrayList<>();
    protected int offset = 0; // offset
    protected int curPage = 0; // 当前页面
    protected int limit = 20; // limit
    public boolean curIsRefresh = true;
    public boolean isFirstOrPull = true; // 首次进来或者手动的时候刷新

    @Override
    protected void initView() {
        super.initView();
        viewDelegate.setEmptyMsg(R.string.common_nodata);
    }

    @Override
    protected void initValue() {
        super.initValue();
        viewDelegate.initValue();
        adapter = getAdapter();
        viewDelegate.setAdapter(adapter);
    }

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        viewDelegate.setRreshOrLoadListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                curIsRefresh = true;
                isFirstOrPull = true;
                reset();
            }
        }, new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                curIsRefresh = false;
                loadMore();
            }
        });
    }

    @Override
    protected void onLazyLoad() {
        if (viewDelegate == null) {
            return;
        }
        if (viewDelegate.reset) {
            reset();
        } else {
            viewDelegate.setAutoRefresh();
        }
    }

    protected void reset() {
        if (!getActivity().isFinishing() && viewDelegate != null) {
            curPage = 0;
            curIsRefresh = true;
            loadMore();
        }
    }

    protected void loadMore() {
        if (!viewDelegate.havaNet()) {
            //无网络时
            if (mList == null || mList.size() == 0) {
                viewDelegate.setViewState(1);
            }
            loadComplete();
        }
        if (!curIsRefresh) {
            curPage++;
        }
        offset = curPage * limit;
        getData();
    }

    protected void setList(List<T> list) {
        if (list == null) {
            loadErrorResult();
            return;
        }
        if (curIsRefresh) {
            mList.clear();
            adapter.clearData();
            if (list.size() == 0) {
                viewDelegate.setViewState(1);
            } else {
                viewDelegate.setViewState(0);
            }
        }
        mList.addAll(list);
        adapter.setDatas(mList);
        if (viewDelegate != null && viewDelegate.mHeaderAndFooterWrapper != null) {
            viewDelegate.mHeaderAndFooterWrapper.notifyDataSetChanged();
        }
        if (!haveMoreOrNot(list)) {
            viewDelegate.setCanLoadMore(false);
        } else {
            viewDelegate.setBoth();
        }
        loadComplete();
        if (viewDelegate.reset) {
            viewDelegate.setCanRefresh(false);
        }
        doAfterReceive();

    }

    //加载完毕
    protected void loadComplete() {
        if (viewDelegate != null) {
            viewDelegate.complete();
        }
    }

    // 设置完数据的其他操作
    protected void doAfterReceive() {

    }

    @Override
    public void onSuccess(CommonEntity<T> result) {
        if (result.getData() != null) {
            ArrayList models = ((ServerStatus) result.getData()).getList();
            setList(models);
            doAfterReceive();
        }
    }

    @Override
    public void onError(String error) {
        loadComplete();
    }

    /**
     * 判断是否还有更多数据
     *
     * @param list
     */
    protected boolean haveMoreOrNot(List list) {
        if (list.size() < limit) {
            return false;
        }
        return true;
    }

    // 数据都显示出来之后的处理
    protected void doAfterDataSet() {

    }

    // id为空，或者数据错误的情况 显示一个空数据
    public void loadErrorResult() {
        if (viewDelegate != null) {
            viewDelegate.complete();
            viewDelegate.setViewState(1);
        }
    }

    protected abstract MultiItemTypeAdapter getAdapter();

    protected abstract void getData();
}
