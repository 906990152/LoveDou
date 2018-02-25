package com.sgl.dou.mvp.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextWatcher;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * Created by Sola on 2017/12/23.
 * 视图层代理的基类
 */

public abstract class AppDelegate implements IDelegate {
    protected final SparseArray<View> mViews = new SparseArray<>();
    protected View rootView;

    public abstract int getRootLayoutId();


    @Override
    public void create(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int rootLayoutId = getRootLayoutId();
        rootView = inflater.inflate(rootLayoutId, container, false);
    }

    @Override
    public View getRootView() {
        return rootView;
    }

    @Override
    public void initWidget() {

    }

    @Override
    public void initVaule() {

    }

    public <T extends View> T bindView(View rView, int viewId) {
        T view = (T) mViews.get(viewId);
        if (view == null) {
            view = (T) rootView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return view;
    }

    public <T extends View> T get(int viewId) {
        return (T) bindView(rootView, viewId);
    }

    public <T extends View> T get(View rView, int viewId) {
        return (T) bindView(rView, viewId);
    }

    public void setOnClickListener(View.OnClickListener listener, int... ids) {
        if (ids == null) {
            return;
        }
        for (int id : ids) {
            get(id).setOnClickListener(listener);
        }
    }

    public void setTextChangedListener(TextWatcher listener, int... ids) {
        if (ids == null) {
            return;
        }
        for (int id : ids) {
            EditText text = get(id);
            text.addTextChangedListener(listener);
        }
    }

    public <T extends AppCompatActivity> T getActivity() {
        return (T) rootView.getContext();
    }
    /**
     *
     * @param visible  是否显示
     * @param views    数组view
     */
    public void setViewGoneOrVisible(boolean visible, final View... views) {
        if (views != null && views.length > 0) {
            for (View view : views) {
                setViewGoneOrVisible(view,visible);
            }
        }
    }
    //单个view
    public void setViewGoneOrVisible(View view, boolean visible){
        if (view != null)
            view.setVisibility(visible ? View.VISIBLE : View.GONE);
    }
}
