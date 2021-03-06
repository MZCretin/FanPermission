package com.cretin.tools.fanpermission;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @date: on 2019-11-13
 * @author: a112233
 * @email: mxnzp_life@163.com
 * @desc: 添加描述
 */
public class FanPermissionConfig implements Parcelable {
    //必须要所有的权限都通过才能通过
    private boolean forceAllPermissionsGranted;
    //设置用户点击不再提示之后的弹窗文案
    private String forceDeniedPermissionTips;

    public String getForceDeniedPermissionTips() {
        return forceDeniedPermissionTips;
    }

    public FanPermissionConfig setForceDeniedPermissionTips(String forceDeniedPermissionTips) {
        this.forceDeniedPermissionTips = forceDeniedPermissionTips;
        return this;
    }

    private FanPermissionUtils check;

    public FanPermissionConfig(FanPermissionUtils check) {
        this.check = check;
    }

    public boolean isForceAllPermissionsGranted() {
        return forceAllPermissionsGranted;
    }

    public FanPermissionConfig setForceAllPermissionsGranted(boolean forceAllPermissionsGranted) {
        this.forceAllPermissionsGranted = forceAllPermissionsGranted;
        return this;
    }

    public FanPermissionUtils buildConfig() {
        return check;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.forceAllPermissionsGranted ? (byte) 1 : (byte) 0);
        dest.writeString(this.forceDeniedPermissionTips);
    }

    protected FanPermissionConfig(Parcel in) {
        this.forceAllPermissionsGranted = in.readByte() != 0;
        this.forceDeniedPermissionTips = in.readString();
    }

    public static final Creator<FanPermissionConfig> CREATOR = new Creator<FanPermissionConfig>() {
        @Override
        public FanPermissionConfig createFromParcel(Parcel source) {
            return new FanPermissionConfig(source);
        }

        @Override
        public FanPermissionConfig[] newArray(int size) {
            return new FanPermissionConfig[size];
        }
    };
}
