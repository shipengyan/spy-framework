package com.github.shipengyan.framework.util.task;

/**
 * Created by yuliang on 2015/10/23.
 */
public interface IBack {

    Object doing(Object... objs) throws Throwable;
}
