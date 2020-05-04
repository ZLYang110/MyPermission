package com.zlyandroid.mypermission;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.zlylib.mypermissionlib.MyPermission;
import com.zlylib.mypermissionlib.RequestInterceptor;
import com.zlylib.mypermissionlib.RequestListener;
import com.zlylib.mypermissionlib.RuntimeRequester;
import com.zlylib.upperdialog.Upper;
import com.zlylib.upperdialog.manager.Layer;

import java.io.File;
/**
 * @author zhangliyang
 * GitHub: https://github.com/ZLYang110
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private RuntimeRequester mRuntimeRequester;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_runtime).setOnClickListener(this);
        findViewById(R.id.btn_install).setOnClickListener(this);
        findViewById(R.id.btn_overlay).setOnClickListener(this);
        findViewById(R.id.btn_setting).setOnClickListener(this);
        findViewById(R.id.btn_notification_show).setOnClickListener(this);
        findViewById(R.id.btn_notification_access).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn_runtime:
                requestRuntime();
                break;
            case R.id.btn_install:
                requestInstall();
                break;
            case R.id.btn_overlay:
                requestOverlay();
                break;
            case R.id.btn_setting:
                requestSetting();
                break;
            case R.id.btn_notification_show:
                requestNotificationShow();
                break;
            case R.id.btn_notification_access:
                requestNotificationAccess();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (mRuntimeRequester != null) {
            mRuntimeRequester.onActivityResult(requestCode);
        }
    }
    private static final int REQ_CODE_PERMISSION = 1;
    private void requestRuntime() {
        PermissionUtils.request(new RequestListener() {
            @Override
            public void onSuccess() {
                // TODO 授权成功
                Toast.makeText(MainActivity.this , "成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailed() {
                // TODO 授权失败
                Toast.makeText(MainActivity.this , "失败", Toast.LENGTH_SHORT).show();
            }
        },MainActivity.this,REQ_CODE_PERMISSION,Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE);

    }

    private void requestInstall() {
        PermissionUtils.requestInstall(new RequestListener() {
            @Override
            public void onSuccess() {
                Toast.makeText(MainActivity.this , "成功", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailed() {
                Toast.makeText(MainActivity.this , "失败", Toast.LENGTH_SHORT).show();
            }
        },MainActivity.this,new File(MainActivity.this.getCacheDir(), "test.apk").getPath());

    }

    private void requestOverlay() {
        PermissionUtils.requestOverlay(new RequestListener() {
            @Override
            public void onSuccess() {
                Toast.makeText(MainActivity.this , "成功", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailed() {
                Toast.makeText(MainActivity.this , "失败", Toast.LENGTH_SHORT).show();
            }
        },MainActivity.this);
    }

    private void requestSetting() {
        PermissionUtils.requestSetting(new RequestListener() {
            @Override
            public void onSuccess() {
                Toast.makeText(MainActivity.this , "成功", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailed() {
                Toast.makeText(MainActivity.this , "失败", Toast.LENGTH_SHORT).show();
            }
        },MainActivity.this);

    }

    private void requestNotificationShow() {
        PermissionUtils.requestNotificationShow(new RequestListener() {
            @Override
            public void onSuccess() {
                Toast.makeText(MainActivity.this , "成功", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailed() {
                Toast.makeText(MainActivity.this , "失败", Toast.LENGTH_SHORT).show();
            }
        },MainActivity.this);
    }

    //定制
    private void requestNotificationAccess() {

        MyPermission.with(this).notificationAccess()
                .onWithoutPermission(new RequestInterceptor<Void>() {
                    @Override
                    public void intercept(@NonNull final Void data, @NonNull final Executor executor) {


                        Upper.dialog(MainActivity.this)
                                .contentView(R.layout.dialog_runtime_before_request)
                                .backgroundColorRes(R.color.dialog_bg)
                                .cancelableOnTouchOutside(false)
                                .cancelableOnClickKeyBack(false)
                                .bindData(new Layer.DataBinder() {

                                    @Override
                                    public void bindData(Layer layer) {
                                        TextView tvTitle = layer.getView(R.id.tv_dialog_permission_title);
                                        TextView tvDescription = layer.getView(R.id.tv_dialog_permission_description);
                                        TextView tvNext = layer.getView(R.id.tv_dialog_permission_next);
                                        tvNext.setText("去打开");
                                        tvTitle.setText("访问通知");
                                        tvDescription.setText("我们将开始请求访问通知权限");
                                    }
                                })
                                .onClickToDismiss(new Layer.OnClickListener() {
                                    @Override
                                    public void onClick(Layer anyLayer, View v) {
                                        executor.execute();
                                    }
                                }, R.id.tv_dialog_permission_next)
                                .onClickToDismiss(new Layer.OnClickListener() {
                                    @Override
                                    public void onClick(Layer anyLayer, View v) {
                                        executor.cancel();
                                    }
                                }, R.id.tv_dialog_permission_close)
                                .show();

                    }
                })
                .request(new RequestListener() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(MainActivity.this , "成功", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailed() {
                        Toast.makeText(MainActivity.this , "失败", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
