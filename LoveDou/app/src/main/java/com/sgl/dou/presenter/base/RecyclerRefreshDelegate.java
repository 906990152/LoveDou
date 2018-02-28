package com.sgl.dou.presenter.base;


import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sgl.dou.R;
import com.sgl.dou.base.DouApp;
import com.sgl.dou.library.recycleview.adapter.MultiItemTypeAdapter;
import com.sgl.dou.library.recycleview.wrapper.HeaderAndFooterWrapper;
import com.sgl.dou.utils.ScreenUtils;

/**
 * Created by Administrator on 2018/2/28 0028.
 */

public abstract class RecyclerRefreshDelegate extends PullRefreshDelegate {
    public static final int HORIZONTAL_LISTVIEW = 0; // 水平列表
    public static final int VERTICAL_LISTVIEW = 1; // 垂直列表
    public static final int GRIDVIEW = 2; // 网格列表
    public static final int HORIZONTAL_STAGGEREDGRID = 3; // 水平瀑布列表
    public static final int VERTICAL_STAGGEREDGRID = 4; // 垂直瀑布列表
    public RecyclerView recyclerView;
    public LinearLayout lyEmpty;
    private TextView tvEmpty;
    private TextView tvFootEmptyMsg;
    private TextView tvBanding;

    public HeaderAndFooterWrapper mHeaderAndFooterWrapper;
    public boolean haveRecycler = true;//是否需要用到recycleview;

    @Override
    public void initWidget() {
        super.initWidget();
        if (!haveRecycler) {
            return;
        }
        recyclerView = get(getRecyclerViewId());
        lyEmpty = get(R.id.ly_empty);
        tvEmpty = get(R.id.tv_empty_msg);
        tvBanding = get(R.id.tv_banding);
    }

    @Override
    public void initValue() {
        super.initValue();
        setListType(VERTICAL_LISTVIEW, 0);
    }

    protected void setListType(int type, int spanCount) {
        setRecyclerViewLayoutManager(recyclerView, type, spanCount);
    }

    protected void setListType(RecyclerView curRview, int type, int spanCount) {
        setRecyclerViewLayoutManager(curRview, type, spanCount);
    }

    // 设置recycleview类型 listview 或者GridView
    private void setRecyclerViewLayoutManager(RecyclerView recyclerView, int type, int spanCount) {
        if (recyclerView == null) {
            return;
        }
        recyclerView.setNestedScrollingEnabled(false);//解决recycleView 和NestedScrollView冲突
        //确定Item的改变不会影响RecyclerView的宽高的时候可以设置setHasFixedSize(true)
        recyclerView.setHasFixedSize(true);
        switch (type) {
            case HORIZONTAL_LISTVIEW:
                LinearLayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
                layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerView.setLayoutManager(layoutManager);
                break;
            case VERTICAL_LISTVIEW:
                LinearLayoutManager layoutManager2 = new LinearLayoutManager(this.getActivity());
                layoutManager2.setOrientation(LinearLayoutManager.VERTICAL);
                layoutManager2.setSmoothScrollbarEnabled(true);
                layoutManager2.setAutoMeasureEnabled(true);
                recyclerView.setLayoutManager(layoutManager2);
                break;
            case GRIDVIEW:
                //要在adapter之前设置
                GridLayoutManager gridLayoutManager = new GridLayoutManager(this.getActivity(), spanCount);
                recyclerView.setLayoutManager(gridLayoutManager);
                break;
            case VERTICAL_STAGGEREDGRID:
                StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(spanCount, StaggeredGridLayoutManager.VERTICAL);
                staggeredGridLayoutManager.setOrientation(StaggeredGridLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(staggeredGridLayoutManager);
                break;
            case HORIZONTAL_STAGGEREDGRID:
                StaggeredGridLayoutManager staggeredGridLayoutManager2 = new StaggeredGridLayoutManager(spanCount, StaggeredGridLayoutManager.HORIZONTAL);
                staggeredGridLayoutManager2.setOrientation(StaggeredGridLayoutManager.HORIZONTAL);
                recyclerView.setLayoutManager(staggeredGridLayoutManager2);
                break;
        }
        setScrollListener(recyclerView);
    }

    //监听recycleview的滑动，来判断是否加载图片
    private void setScrollListener(RecyclerView recyclerView) {
        if (recyclerView == null) {
            return;
        }
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                // 滑动时停止加载图片
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    Glide.with(DouApp.context).resumeRequests();
                } else {
                    Glide.with(DouApp.context).pauseRequests();
                }
            }
        });
    }

    public void setAdapter(MultiItemTypeAdapter adapter) {
        setAdapter(recyclerView, adapter);
    }

    public void setAdapter(RecyclerView curRview, MultiItemTypeAdapter adapter) {
        if (curRview == null) {
            return;
        }
        if (mHeaderAndFooterWrapper != null) {
            curRview.setAdapter(mHeaderAndFooterWrapper);
        } else {
            curRview.setAdapter(adapter);
        }

    }

    /**
     * 头部根据网络请求增减时用
     * @param views
     * @param adapter
     */
    protected void addHeadView(MultiItemTypeAdapter adapter, View... views) {
        if (adapter == null) {
            return;
        }
        if (mHeaderAndFooterWrapper == null) {
            mHeaderAndFooterWrapper = new HeaderAndFooterWrapper(adapter);
        }
        addHeadView(views);
        setAdapter(adapter);
    }
    // 头部固定不变时
    public void addDefaultHeadView(MultiItemTypeAdapter adapter ,View... views){
        if (adapter == null){
            return;
        }
        if (mHeaderAndFooterWrapper == null){
            mHeaderAndFooterWrapper = new HeaderAndFooterWrapper(adapter);
        }
        addHeadView(views);
    }

    private void addHeadView(View... views) {
        if (views.length > 0) {
            for (View view : views) {
                if (view != null) {
                    mHeaderAndFooterWrapper.addHeaderView(view);
                }
            }
        }
    }
    /**
     * 数据加载情况
     * @param state 1 空 0 有内容 2 错误
     */
    public void setViewState(int state){
        if (state==1){
            if (mHeaderAndFooterWrapper!=null){
                mHeaderAndFooterWrapper.addHeaderView(tvFootEmptyMsg);
            }else {
                recyclerView.setVisibility(View.GONE);
                lyEmpty.setVisibility(View.VISIBLE);
            }
        }else {
            recyclerView.setVisibility(View.VISIBLE);
            lyEmpty.setVisibility(View.GONE);
            if (mHeaderAndFooterWrapper!=null&& tvFootEmptyMsg != null&&mHeaderAndFooterWrapper.getHeadersCount()>1){
                //有固定头部且下面是列表时
                mHeaderAndFooterWrapper.deleteHeaderView(tvFootEmptyMsg);
            }
        }

    }
    /**
     * 设置空数据的视图和内容
     * @param strId
     */
    public void setEmptyMsg(int strId){
        setEmptyMsgAndBtn(false,strId,0);
    }
    /**
     * 设置空按钮还有消息
     * @param btnMsg 0 为隐藏
     * @param msg
     */
    public void setEmptyMsgAndBtn(boolean showBtn,int msg,int btnMsg){
        if (mHeaderAndFooterWrapper != null){
            tvFootEmptyMsg = new TextView(this.getActivity());
            tvFootEmptyMsg.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ScreenUtils.getScreenHeight(this.getActivity())/3));
            tvFootEmptyMsg.setText(getString(msg));
            tvFootEmptyMsg.setGravity(Gravity.CENTER);
            tvFootEmptyMsg.setTextSize(14);
            tvFootEmptyMsg.setTextColor(ContextCompat.getColor(this.getActivity(),R.color.color_tv_gray_99));
        }else {
            if (msg != 0) {
                tvEmpty.setText(getString(msg));
            } else {
                tvEmpty.setText("");
            }
            if (showBtn) {
                setViewGoneOrVisible(tvBanding, true);
                if (btnMsg != 0) {
                    tvBanding.setText(getString(btnMsg));
                }
            } else {
                setViewGoneOrVisible(tvBanding, false);
            }
        }
    }
    public String getString(int string) {
        return getActivity().getResources().getString(string);
    }
    /**
     * 设置背景颜色
     * @param color
     */
    protected void setRecyclerViewBgColor(int color){
        recyclerView.setBackgroundColor(ContextCompat.getColor(this.getActivity(), color));
    }


    //是否要动态还是要写成固定的view_id
    protected abstract int getRecyclerViewId();
}
