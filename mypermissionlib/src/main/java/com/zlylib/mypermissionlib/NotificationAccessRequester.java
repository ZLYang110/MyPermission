package com.zlylib.mypermissionlib;

import android.content.Context;

import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RequestExecutor;
import com.yanzhenjie.permission.option.Option;

/**
 * 描述：访问通知权限
 *
 * @author zhangliyang
 */
public class NotificationAccessRequester implements Requester<Void> {

    private final Option mOption;

    private RequestInterceptor<Void> mOnWithoutPermission = null;

    NotificationAccessRequester(Option option) {
        this.mOption = option;
    }
    /**
     * 未授予权限时，在跳转设置页面之前调用
     **/
    public NotificationAccessRequester onWithoutPermission(RequestInterceptor<Void> onWithoutPermission) {
        mOnWithoutPermission = onWithoutPermission;
        return this;
    }

    @Override
    public Void request(final RequestListener listener) {
        mOption.notification().listener()
                .rationale(new Rationale<Void>() {
                    @Override
                    public void showRationale(Context c, Void d, final RequestExecutor e) {
                        if (mOnWithoutPermission == null) {
                            e.execute();
                        } else {
                            mOnWithoutPermission.intercept(d, new RequestInterceptor.Executor() {
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
                .onGranted(new Action<Void>() {
                    @Override
                    public void onAction(Void data) {
                        listener.onSuccess();
                    }
                })
                .onDenied(new Action<Void>() {
                    @Override
                    public void onAction(Void data) {
                        listener.onFailed();
                    }
                })
                .start();
        return null;
    }
}
