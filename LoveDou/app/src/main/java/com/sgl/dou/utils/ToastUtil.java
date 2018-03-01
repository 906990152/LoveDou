package com.sgl.dou.utils;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sgl.dou.base.DouApp;


/**
 * Created by android2 on 2015/8/27.
 */
public class ToastUtil {

    private Toast mToast;
    private TextView textView;
    private ImageView imageView;


    private ToastUtil(Context context, CharSequence text, boolean errorOrNot, int duration) {
//        View v = LayoutInflater.from(context).inflate(R.layout.layout_toast, null);
//        textView = v.findViewById(R.id.tv_msg);
//        imageView = v.findViewById(R.id.iv_icon);
//        if (errorOrNot) {
//            imageView.setImageResource(R.drawable.toast_error);
//        } else {
//            imageView.setImageResource(R.drawable.toast_right);
//        }
//        textView.setText(text);
//        mToast = new Toast(context);
//        mToast.setDuration(duration);
//        mToast.setGravity(Gravity.CENTER,0,0);
//        mToast.setView(v);
    }

    public static ToastUtil makeText(Context context, CharSequence text, boolean errorOrNot, int duration) {
        return new ToastUtil(context, text, errorOrNot, duration);
    }

    public void show() {
        if (mToast != null) {
            mToast.show();
        }
    }

    public void setGravity(int gravity, int xOffset, int yOffset) {
        if (mToast != null) {
            mToast.setGravity(gravity, xOffset, yOffset);
        }
    }


    /**
     * 显示错误类型的吐司
     *
     * @param str
     */
    public static void showErrorToast(int str) {
        makeText(DouApp.context, DouApp.context.getResources().getText(str), true, Toast.LENGTH_SHORT).show();
    }

    // 显示错误类型的吐司
    public static void showErrorToast(String content) {
        if (!StringUtil.isEmpty(content)) {
            makeText(DouApp.context, content, true, Toast.LENGTH_SHORT).show();
        }
    }

    // 显示正确类型的吐司
    public static void showRightToast(int str) {
        makeText(DouApp.context, DouApp.context.getResources().getText(str), false, Toast.LENGTH_SHORT).show();
    }

    // 显示正确类型的吐司
    public static void showRightToast(String content) {
        if (!StringUtil.isEmpty(content)) {
            makeText(DouApp.context, content, false, Toast.LENGTH_SHORT).show();
        }

    }

    public static void showToast(int str) {
        Toast.makeText(DouApp.context, DouApp.context.getResources().getText(str), Toast.LENGTH_SHORT).show();
    }

    /**
     * 根据状态显示错误或者正确的吐司
     * @param str
     * @param status 0 正确
     */
    public static void showToast(String str,int status) {
        if (status == 0){
            showRightToast(str);
        }else {
            showErrorToast(str);
        }
    }
}
