package com.cretin.fanpermission;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.cretin.tools.fanpermission.FanPermissionListener;
import com.cretin.tools.fanpermission.FanPermissionUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.tv_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Switch aSwitch = findViewById(R.id.switch_view);

                FanPermissionUtils.with(MainActivity.this)
                        //添加所有你需要申请的权限
                        .addPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .addPermissions(Manifest.permission.ACCESS_FINE_LOCATION)
                        .addPermissions(Manifest.permission.CALL_PHONE)
                        .addPermissions(Manifest.permission.ACCESS_WIFI_STATE)
                        .addPermissions(Manifest.permission.CAMERA)
                        //添加权限申请回调监听 如果申请失败 会返回已申请成功的权限列表，用户拒绝的权限列表和用户点击了不再提醒的永久拒绝的权限列表
                        .setPermissionsCheckListener(new FanPermissionListener() {
                            @Override
                            public void permissionRequestSuccess() {
                                //所有权限授权成功才会回调这里
                                ((TextView) findViewById(R.id.tv_result)).setText("授权结果\n\n所有权限都授权成功\n");
                                Toast.makeText(MainActivity.this, "所有权限都授权成功", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void permissionRequestFail(String[] grantedPermissions, String[] deniedPermissions, String[] forceDeniedPermissions) {
                                //当有权限没有被授权就会回调这里
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
                                ((TextView) findViewById(R.id.tv_result)).setText(result);
                                Toast.makeText(MainActivity.this, "授权失败", Toast.LENGTH_SHORT).show();
                            }
                        })
                        //生成配置
                        .createConfig()
                        //配置是否强制用户授权才可以使用，当设置为true的时候，如果用户拒绝授权，会一直弹出授权框让用户授权
                        .setForceAllPermissionsGranted(aSwitch.isChecked())
                        //配置当用户点击了不再提示的时候，会弹窗指引用户去设置页面授权，这个参数是弹窗里面的提示内容
                        .setForceDeniedPermissionTips("请前往设置->应用->【" + FanPermissionUtils.getAppName(MainActivity.this) + "】->权限中打开相关权限，否则功能无法正常运行！")
                        //构建配置并生效
                        .buildConfig()
                        //开始授权
                        .startCheckPermission();
            }
        });


        findViewById(R.id.tv_to_fragment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FragmentActivity.class));
            }
        });
    }
}
