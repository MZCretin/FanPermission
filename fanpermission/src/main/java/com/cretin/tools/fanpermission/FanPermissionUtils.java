package com.cretin.tools.fanpermission;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.ArrayList;
import java.util.List;

/**
 * @date: on 2019-11-11
 * @author: a112233
 * @email: mxnzp_life@163.com
 * @desc: 权限检查主要帮助类
 */
public class FanPermissionUtils {
    //宿主Activity
    private Activity mContext;
    //回调监听
    private FanPermissionListener listener;

    private FanPermissionConfig checkConfig;


    private FanPermissionUtils(Activity mContext) {
        this.mContext = mContext;
    }

    public static FanPermissionUtils with(Activity context) {
        return new FanPermissionUtils(context);
    }

    private List<String> permissions = new ArrayList<>();

    public FanPermissionConfig createConfig() {
        checkConfig = new FanPermissionConfig(this);
        return checkConfig;
    }

    /**
     * 添加权限
     *
     * @param permission
     */
    public FanPermissionUtils addPermissions(String permission) {
        if (!permissions.contains(permission))
            permissions.add(permission);
        return this;
    }

    /**
     * 添加监听
     *
     * @param listener
     */
    public FanPermissionUtils setPermissionsCheckListener(FanPermissionListener listener) {
        this.listener = listener;
        return this;
    }

    /**
     * 开始申请权限
     */
    public void startCheckPermission() {
        FanPermissionFragment.newInstance(permissions.toArray(new String[permissions.size()]), checkConfig).setPermissionCheckListener(listener).start(mContext);
    }

    /**
     * 获取App的名称
     *
     * @param context 上下文
     *
     * @return 名称
     */
    public static String getAppName(Context context) {
        PackageManager pm = context.getPackageManager();
        //获取包信息
        try {
            PackageInfo packageInfo = pm.getPackageInfo(context.getPackageName(), 0);
            //获取应用 信息
            ApplicationInfo applicationInfo = packageInfo.applicationInfo;
            //获取albelRes
            int labelRes = applicationInfo.labelRes;
            //返回App的名称
            return context.getResources().getString(labelRes);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }
}
