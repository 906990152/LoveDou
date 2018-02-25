package com.sgl.dou.Delegate;

import android.widget.TextView;

import com.sgl.dou.R;
import com.sgl.dou.mvp.View.AppDelegate;

/**
 * Created by Sola on 2017/12/23.
 */

public class BlankFragDelegate extends AppDelegate {
    private TextView mTv;
    @Override
    public int getRootLayoutId() {
        return R.layout.frag_blank;
    }

    @Override
    public void initWidget() {
        super.initWidget();
        mTv=get(R.id.tv);
    }

    public void setTextView(String content){
        mTv.setText(content);
    }
}
