package com.sgl.dou;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.Window;

import com.sgl.dou.Delegate.MainActDelegate;
import com.sgl.dou.fragment.BlankFragment;
import com.sgl.dou.library.permission.PermissionCallback;
import com.sgl.dou.library.zxing.app.CaptureActivity;
import com.sgl.dou.mvp.base.BaseAcitvity;
import com.sgl.dou.utils.PermissionUtils;

import java.util.ArrayList;

public class MainActivity extends BaseAcitvity<MainActDelegate> implements View.OnClickListener {
    private String[] names = {"属性技能", "推荐出装", "攻略"};
    private ArrayList<Fragment> fragments = new ArrayList<>();
    @Override
    protected Class<MainActDelegate> getDelegateClass() {
        return MainActDelegate.class;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        super.initView();
        BlankFragment first=BlankFragment.newInstance("1");
        fragments.add(first);
        BlankFragment second=BlankFragment.newInstance("2");
        fragments.add(second);
        BlankFragment third=BlankFragment.newInstance("3");
        fragments.add(third);
        viewDelegate.setViewAdapter(names,fragments);
        viewDelegate.setmTv("sldfjsjfdls");
    }

    @Override
    protected void bindEventListener() {
        super.bindEventListener();
        viewDelegate.setOnClickListener(this,R.id.t);
    }

    @Override
    public void onClick(View view) {
        int id=view.getId();
        if (id==R.id.t){
            PermissionUtils.checkPermission(this, PermissionUtils.START_PERMISSIONS, new PermissionCallback() {
                @Override
                public void success() {
                    Intent intent=new Intent(viewDelegate.getActivity(), CaptureActivity.class);
                    startActivity(intent);
                }
            });

        }

    }
}
