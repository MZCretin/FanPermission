package com.cretin.tools.fanpermission;

/**
 * @date: on 2019-11-11
 * @author: a112233
 * @email: mxnzp_life@163.com
 * @desc: 授权反馈事件
 */
public interface FanPermissionListener {
    /*
     * 授权全部通过
     */
    void permissionRequestSuccess();

    /*
     * 授权未通过
     * @param grantedPermissions 已通过的权限
     * @param deniedPermissions 拒绝的权限
     * @param forceDeniedPermissions 永久拒绝的权限（也就是用户点击了不再提醒的那些权限）
     */
    void permissionRequestFail(String[] grantedPermissions, String[] deniedPermissions, String[] forceDeniedPermissions);
}
