package com.sgl.dou.fragment;

import android.os.Bundle;
import android.util.Log;

import com.sgl.dou.delegate.BlankFragDelegate;
import com.sgl.dou.presenter.base.BaseFragment;

/**
 * Created by Sola on 2017/12/23.
 */

public class BlankFragment extends BaseFragment<BlankFragDelegate> {
    private static final String TYPE = "type";
    private String type;

    public static BlankFragment newInstance(String type) {
        BlankFragment blankFragment = new BlankFragment();
        Bundle bundle = new Bundle();
        bundle.putString(TYPE, type);
        blankFragment.setArguments(bundle);
        return blankFragment;
    }

    @Override
    protected void getIntentData() {
        super.getIntentData();
        type = getArguments().getString(TYPE);
        Log.e("sgl", "getIntentData===" + type);
    }


    @Override
    protected Class<BlankFragDelegate> getDelegateClass() {
        return BlankFragDelegate.class;
    }

    @Override
    protected void initValue() {
        super.initValue();
        Log.e("sgl", "initValue===" + type);
        viewDelegate.setTextView(type);
    }

    @Override
    protected void onLazyLoad() {
        super.onLazyLoad();
        Log.e("sgl", "onLazyLoad===" + type);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("sgl", "onResume===" + type);
    }
}
