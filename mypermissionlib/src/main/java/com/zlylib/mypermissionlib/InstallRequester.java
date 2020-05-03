package com.zlylib.mypermissionlib;

import android.content.Context;

import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RequestExecutor;
import com.yanzhenjie.permission.option.Option;

import java.io.File;

/**
 * 描述：未知应用安装权限
 *
 * @author zhangliyang
 */
public class InstallRequester implements Requester<Void> {

    private final Option mOption;
    private final File mApkFile;

    private RequestInterceptor<File> mOnWithoutPermission = null;

    InstallRequester(Option option, File apkFile) {
        this.mOption = option;
        this.mApkFile = apkFile;
    }
    /**
     * 未授予权限时，在跳转设置页面之前调用
     **/
    public InstallRequester onWithoutPermission(RequestInterceptor<File> onWithoutPermission) {
        mOnWithoutPermission = onWithoutPermission;
        return this;
    }
    /**
     * 开始申请
     **/
    @Override
    public Void request(final RequestListener listener) {
        mOption.install()
                .file(mApkFile)
                .rationale(new Rationale<File>() {
                    @Override
                    public void showRationale(Context c, File f, final RequestExecutor e) {
                        if (mOnWithoutPermission == null) {
                            e.execute();
                        } else {
                            mOnWithoutPermission.intercept(f, new RequestInterceptor.Executor() {
                                @Override
                                public void execute() {
                                    e.execute();
                                }

                                @Override
                                public void cancel() {
                                    e.cancel();
                                }
                            });
                        }
                    }
                })
                .onGranted(new Action<File>() {
                    @Override
                    public void onAction(File data) {
                        listener.onSuccess();
                    }
                })
                .onDenied(new Action<File>() {
                    @Override
                    public void onAction(File data) {
                        listener.onFailed();
                    }
                })
                .start();
        return null;
    }
}
