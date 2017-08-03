package com.github.shipengyan.framework.util;

import com.github.shipengyan.framework.util.task2.TaskEntity;
import com.github.shipengyan.framework.util.task2.TaskPoolManager;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 基于线程池队列的任务
 *
 * @author shi.pengyan
 * @version 1.0 2017-08-03 9:09
 * @since 1.0
 */
@Slf4j
public class Task2Util {


    /**
     * 添加异步任务(任务列表)
     *
     * @param taskList
     */
    public static void addTaskList(List<TaskEntity> taskList) {
        TaskPoolManager.newInstance().addTasks(taskList);
    }

    /**
     * 添加异步任务(单个任务)
     *
     * @param task
     */
    public static void addTask(TaskEntity task) {
        TaskPoolManager.newInstance().addTask(task);
    }

}
