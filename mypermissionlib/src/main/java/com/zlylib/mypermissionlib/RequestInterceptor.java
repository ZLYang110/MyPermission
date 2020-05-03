package com.zlylib.mypermissionlib;


import androidx.annotation.NonNull;

/**
 * 描述：
 *
 * @author zhangliyang
 */
public interface RequestInterceptor<T> {
    void intercept(@NonNull final T data, @NonNull final Executor executor);

    interface Executor {
        void execute();
        void cancel();
    }
}
