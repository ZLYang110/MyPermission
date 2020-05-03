package com.zlylib.mypermissionlib;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.runtime.Permission;

import java.io.File;

/**
 * @author zhangliyang
 * GitHub: https://github.com/ZLYang110
 */
public class MyPermission {

    private final ContextHolder mContextHolder;

    public static MyPermission with(@NonNull final Context context) {
        return new MyPermission(context);
    }

    public static MyPermission with(@NonNull final Activity activity) {
        return new MyPermission(activity);
    }

    public static MyPermission with(@NonNull final Fragment fragment) {
        return new MyPermission(fragment);
    }

    public static MyPermission with(@NonNull final android.app.Fragment fragment) {
        return new MyPermission(fragment);
    }

    private MyPermission(final Context context){
        mContextHolder = new ContextHolder(context);
    }

    private MyPermission(final Activity activity){
        mContextHolder = new ContextHolder(activity);
    }

    private MyPermission(final Fragment fragment){
        mContextHolder = new ContextHolder(fragment);
    }

    private MyPermission(final android.app.Fragment fragment){
        mContextHolder = new ContextHolder(fragment);
    }
    /**
     * 获取某一权限的名称
     **/
    public String name(String permission) {
        return Permission.transformText(mContextHolder.getContext(), permission).get(0);
    }
    /**
     * 获取文件对应的Uri
     **/
    public Uri fileUri(File file) {
        return AndPermission.getFileUri(mContextHolder.getContext(), file);
    }
    /**
     * 获取运行时权限申请的实现类实例
     **/
    public RuntimeRequester runtime(int requestCodeWhenGoSetting) {
        return new RuntimeRequester(mContextHolder.getOption(), mContextHolder.getContext(), requestCodeWhenGoSetting);
    }
    /**
     * 获取未知应用安装权限申请的实现类实例
     **/
    public InstallRequester install(File apkFile) {
        return new InstallRequester(mContextHolder.getOption(), apkFile);
    }

    /**
     * 获取悬浮窗权限申请的实现类实例
     **/
    public OverlayRequester overlay() {
        return new OverlayRequester(mContextHolder.getOption());
    }

    @Deprecated
    public SettingRequester setting() {
        return new SettingRequester(mContextHolder.getOption());
    }
    /**
     * 获取显示通知权限申请的实现类实例
     **/
    public NotificationShowRequester notificationShow() {
        return new NotificationShowRequester(mContextHolder.getOption());
    }
    /**
     * 获取访问通知权限申请的实现类实例
     **/
    public NotificationAccessRequester notificationAccess() {
        return new NotificationAccessRequester(mContextHolder.getOption());
    }

}
