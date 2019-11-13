package com.cretin.fanpermission.fragment;


import android.Manifest;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.cretin.fanpermission.MainActivity;
import com.cretin.fanpermission.R;
import com.cretin.tools.fanpermission.FanPermissionListener;
import com.cretin.tools.fanpermission.FanPermissionUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class DemoFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_demo, container, false);
        view.findViewById(R.id.tv_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                Switch aSwitch = view.findViewById(R.id.switch_view);

                FanPermissionUtils.with(getActivity())
                        .addPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .addPermissions(Manifest.permission.ACCESS_FINE_LOCATION)
                        .addPermissions(Manifest.permission.CALL_PHONE)
                        .addPermissions(Manifest.permission.ACCESS_WIFI_STATE)
                        .addPermissions(Manifest.permission.CAMERA)
                        .setPermissionsCheckListener(new FanPermissionListener() {
                            @Override
                            public void permissionRequestSuccess() {
                                ((TextView) view.findViewById(R.id.tv_result)).setText("授权结果\n\n所有权限都授权成功\n");
                                Toast.makeText(getActivity(), "所有权限都授权成功", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void permissionRequestFail(String[] grantedPermissions, String[] deniedPermissions, String[] forceDeniedPermissions) {
                                StringBuilder result = new StringBuilder("授权结果\n\n授权失败\n\n");
                                result.append("授权通过的权限：\n");
                                for (String grantedPermission : grantedPermissions) {
                                    result.append(grantedPermission + "\n");
                                }
                                result.append("\n临时拒绝的权限：\n");
                                for (String deniedPermission : deniedPermissions) {
                                    result.append(deniedPermission + "\n");
                                }
                                result.append("\n永久拒绝的权限：\n");
                                for (String forceDeniedPermission : forceDeniedPermissions) {
                                    result.append(forceDeniedPermission + "\n");
                                }
                                ((TextView) view.findViewById(R.id.tv_result)).setText(result);
                                Toast.makeText(getActivity(), "授权失败", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .createConfig()
                        .setForceAllPermissionsGranted(aSwitch.isChecked())
                        .setForceDeniedPermissionTips("请前往设置->应用->【" + FanPermissionUtils.getAppName(getActivity()) + "】->权限中打开相关权限，否则功能无法正常运行！")
                        .buildConfig()
                        .startCheckPermission();
            }
        });
        return view;
    }

}
