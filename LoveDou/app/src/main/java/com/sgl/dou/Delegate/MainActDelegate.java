package com.sgl.dou.Delegate;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.sgl.dou.BaseFragmentActivity;
import com.sgl.dou.R;
import com.sgl.dou.fragment.BlankFragment;
import com.sgl.dou.mvp.View.AppDelegate;

import java.util.ArrayList;

/**
 * Created by Sola on 2017/12/23.
 */

public class MainActDelegate extends AppDelegate {
    private ViewPager viewPager;
    private SlidingTabLayout tabLayout;
    private TextView mTv;

    @Override
    public int getRootLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initWidget() {
        super.initWidget();
        tabLayout=get(R.id.tab);
        viewPager=get(R.id.view_pager);
        mTv=get(R.id.t);
    }
    public void setmTv(String content){
        mTv.setText(content);
    }

    public void setViewAdapter(String[] names, ArrayList<Fragment> mFragments) {
        tabLayout.setViewPager(viewPager, names, this.getActivity(), mFragments);
    }
}
