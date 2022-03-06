package com.yihang.serp.model;

/**
 * @Author: yihangjou(周逸航)
 * @Site: www.yihang.ml
 * @cnBlogs: https://www.cnblogs.com/yihangjou/
 * @Date: create in 2020/2/24 13:59
 */
public class Meta {
    private boolean KeepAlive;

    private boolean requireAuth;

    public boolean isKeepAlive() {
        return KeepAlive;
    }

    public void setKeepAlive(boolean keepAlive) {
        KeepAlive = keepAlive;
    }

    public boolean isRequireAuth() {
        return requireAuth;
    }

    public void setRequireAuth(boolean requireAuth) {
        this.requireAuth = requireAuth;
    }
}
