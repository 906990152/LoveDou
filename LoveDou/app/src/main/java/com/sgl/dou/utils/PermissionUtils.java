package com.sgl.dou.utils;

import android.Manifest;
import android.app.Activity;

import com.sgl.dou.library.permission.Permission;
import com.sgl.dou.library.permission.PermissionCallback;

/**
 * Created by Sola on 2018/1/3.
 */

public class PermissionUtils {
    // 最开始需要定位和读取数据
    public static final String[] START_PERMISSIONS = {Manifest.permission.CAMERA};
    public static void checkPermission(Activity activity, String[] permissions, PermissionCallback callback){
        Permission.getInstance(activity).checkMutiPermission(permissions,callback);
    }

}
