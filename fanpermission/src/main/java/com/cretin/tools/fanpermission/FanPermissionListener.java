package com.cretin.tools.fanpermission;

/**
 * @date: on 2019-11-11
 * @author: a112233
 * @email: mxnzp_life@163.com
 * @desc: 添加描述
 */
public interface FanPermissionListener {
    void permissionRequestSuccess();

    void permissionRequestFail(String[] grantedPermissions, String[] deniedPermissions, String[] forceDeniedPermissions);
}
