package com.sgl.dou.library.permission;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sola on 2018/1/3.
 */

public class Permission {
    private PermissionCallback callback;
    private String[] permission;
    private Activity activity;
    private static Permission instance=null;
    public Permission(Activity activity){
        this.activity=activity;
    }
    public static Permission getInstance(Activity activity){
        if (instance==null){
            synchronized (Permission.class){
                if (instance==null){
                    instance=new Permission(activity);
                }
            }
        }
        return instance;
    }


    public void checkMutiPermission(String[] permissions, PermissionCallback callback) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            if (callback != null) {
                callback.success();
            }
        } else {
            List<String> needPermissions = getDeniedPermissions(activity, permissions);
            if (needPermissions.size() > 0) {
                this.permission = needPermissions.toArray(new String[needPermissions.size()]);
                this.callback = callback;
                startActivity();
            } else {
                callback.success();
            }
        }

    }

    /**
     * 获取权限集中需要申请权限的列表
     *
     * @param permissions
     * @return
     */
    private List<String> getDeniedPermissions(Activity activity, String[] permissions) {
        List<String> needRequestPermissionList = new ArrayList<>();
        for (String permission : permissions) {
            if (!checkPermission(activity, permission) ||
                    ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
                needRequestPermissionList.add(permission);
            }
        }
        return needRequestPermissionList;
    }

    private boolean checkPermission(Context context, String permission) {
        int checkResult = ContextCompat.checkSelfPermission(context, permission);
        if (checkResult == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        return false;
    }

    private void startActivity() {
        PermissionActivity.setCallback(callback);
        Intent intent = new Intent(activity, PermissionActivity.class);
        intent.putExtra(ConstantValue.DATA_PERMISSION, permission);
        activity.startActivity(intent);
    }
}
