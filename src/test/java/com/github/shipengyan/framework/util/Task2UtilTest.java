package com.github.shipengyan.framework.util;

import com.github.shipengyan.framework.util.task2.TaskEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 模块名
 *
 * @author shi.pengyan
 * @version 1.0 2017-08-03 9:08
 * @since 1.0
 */
@Slf4j
public class Task2UtilTest {


    @Test
    public void run19() throws Exception {

        List<TaskEntity>    taskList  = new ArrayList<>();
        TaskEntity          task      = null;
        Map<String, String> taskParam = null;

        for (int i = 0; i < 5; i++) {
            task = new TaskEntity();
            task.setTaskClass(SendSMS.class);
            task.setTaskMethod("send");
            taskParam = new HashMap<>();
            taskParam.put("phone", "1888888888" + i);
            taskParam.put("content", "测试内容" + i);
            task.setTaskParam(taskParam);
            taskList.add(task);
        }
        /**将任务添加到队列**/
        Task2Util.addTaskList(taskList);

        System.in.read();

    }

    public static class SendSMS {
        /**
         * 向指定手机号发送短信
         */
        public void send(Map<String, String> taskParam) {
            /**获取手机号和发送的内容**/
            String phone   = taskParam.get("phone");
            String content = taskParam.get("content");
            log.debug("向手机号:{}发送短信,内容是:{}", phone, content);
        }
    }


}
