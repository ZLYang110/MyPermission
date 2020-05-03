package com.zlylib.mypermissionlib;

/**
 * 描述：
 *
 * @author zhangliyang
 */
interface Requester<R> {
    R request(RequestListener listener);
}
