package com.github.shipengyan.framework.util.task2;

import java.util.Map;

/**
 * 模块名
 *
 * @author shi.pengyan
 * @version 1.0 2017-08-03 9:04
 * @since 1.0
 */
public class TaskEntity {

    private Class<?>  taskClass;
    private String    taskMethod;
    private Map<?, ?> taskParam;

    public TaskEntity() {
    }

    public TaskEntity(Class<?> taskClass, String taskMethod, Map<?, ?> taskParam) {
        this.taskClass = taskClass;
        this.taskMethod = taskMethod;
        this.taskParam = taskParam;
    }

    public Class<?> getTaskClass() {
        return taskClass;
    }

    public void setTaskClass(Class<?> taskClass) {
        this.taskClass = taskClass;
    }

    public String getTaskMethod() {
        return taskMethod;
    }

    public void setTaskMethod(String taskMethod) {
        this.taskMethod = taskMethod;
    }

    public Map<?, ?> getTaskParam() {
        return taskParam;
    }

    public void setTaskParam(Map<?, ?> taskParam) {
        this.taskParam = taskParam;
    }

}
