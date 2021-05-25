package com.redis.springdataredis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * 测试 Redis 当中的 Hash 类型
 * @author Cheng Qian on 2021/5/25 15:51
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Component
public class TestRedisHash {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 向 Redis 存放 Hash 类型的值
     */
    @Test
    public void setHashValue(){
        redisTemplate.boundHashOps("hashKey").put("name", "唐僧");
        redisTemplate.boundHashOps("hashKey").put("age", "28");
        redisTemplate.boundHashOps("hashKey").put("height", "180cm");
        redisTemplate.boundHashOps("hashKey").put("title", "master");
    }

    /**
     * 获取所有的 Hash key
     */
    @Test
    public void getAllHashKeys(){
        Set keys = redisTemplate.boundHashOps("hashKey").keys();
        System.out.println(keys);
    }

    /**
     * 获取所有的 Hash value
     */
    @Test
    public void getAllHashValues(){
        List values = redisTemplate.boundHashOps("hashKey").values();
        System.out.println(values);
    }

    /**
     * 根据 hash key 取值
     */
    @Test
    public void getHashValueByKey(){
        String str = (String) redisTemplate.boundHashOps("hashKey").get("name");
        System.out.println(str);
    }

    /**
     * 移除 hash key 和它的值
     */
    @Test
    public void removeHashValueByKey(){
        redisTemplate.boundHashOps("hashKey").delete("title");
    }
}
