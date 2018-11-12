package com.github.shipengyan.framework.cache.redis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import redis.clients.jedis.Jedis;

import java.lang.reflect.Type;
import java.util.Set;
import java.util.UUID;

/**
 * 模块名
 *
 * @author spy
 * @version 1.0 2018/11/12
 * @since 1.0
 */
public class RedisDelayingQueue<T> {
    static class TaskItem<T> {
        public String id;
        public T msg;
    }

    // fastjson 序列化对象中存在 generic 类型时，需要使用 TypeReference
    private Type TaskType = new TypeReference<TaskItem<T>>() {
    }.getType();

    private Jedis jedis;
    private String queueKey;

    public RedisDelayingQueue(Jedis jedis, String queueKey) {
        this.jedis = jedis;
        this.queueKey = queueKey;
    }

    public void delay(T msg) {
        TaskItem<T> task = new TaskItem<T>();
        task.id = UUID.randomUUID().toString(); // 分配唯一的 uuid
        task.msg = msg;
        String s = JSON.toJSONString(task); // fastjson 序列化
        jedis.zadd(queueKey, System.currentTimeMillis() + 5000, s); // 塞入延时队列 ,5s 后再试
    }

    public void loop(DelayingQueueCallback<T> callback) {
        while (!Thread.interrupted()) {
            // 只取一条
            Set<String> values = jedis.zrangeByScore(queueKey, 0, System.currentTimeMillis(), 0, 1);
            if (values.isEmpty()) {
                try {
                    Thread.sleep(500); // 歇会继续
                } catch (InterruptedException e) {
                    break;
                }
                continue;
            }
            String s = values.iterator().next();
            if (jedis.zrem(queueKey, s) > 0) { // 抢到了
                TaskItem<T> task = JSON.parseObject(s, TaskType); // fastjson 反序列化
                callback.handleMsg(task.msg);
            }
        }
    }


    public interface DelayingQueueCallback<T> {
        void handleMsg(T msg);
    }
}
