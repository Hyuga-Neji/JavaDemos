package com.publish;

import redis.clients.jedis.Jedis;

/**
 * @author Cheng Qian on 2021/5/12 16:13
 */
public class RedisPublish {

    public static void main(String[] args) {
        // 创建 Jedis
        Jedis jedis = new Jedis("10.74.40.3", 6379);
        jedis.publish("CCTV-1", "我们将于下月中旬上映《宝宝巴士》");
        System.out.println("消息发布完毕..");
    }

}
