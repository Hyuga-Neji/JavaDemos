package com.subscribe;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Cheng Qian on 2021/5/12 15:44
 */
public class RedisSubScribe extends JedisPubSub {

    /**
     * 当订阅者接收到消息时回自动调用改方法
     * @param channel 频道的名称
     * @param message 发布的消息
     */
    @Override
    public void onMessage(String channel, String message) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("我是订阅者：订阅频道【" + channel + "】, 收到的消息是:【" + message + "】, 收到消息的时间为:【" + df.format(new Date()) + "】");
    }

    public static void main(String[] args) {
        // 创建 Jedis
        Jedis jedis = new Jedis("10.74.40.3", 6379);
        // 创建 redisSubScribe 对象
        RedisSubScribe redisSubScribe = new RedisSubScribe();
        // 从Redis订阅
        jedis.subscribe(redisSubScribe, "CCTV-1");
    }
}
