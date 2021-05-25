package com.redis.springdataredis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Set;

/**
 * 测试 Redis 当中的 set 类型
 * @author Cheng Qian on 2021/5/21 16:14
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Component
public class TestRedisSet {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 增加 数据
     */
    @Test
    public void setValue(){
        redisTemplate.boundSetOps("nameSet").add("张三");
        redisTemplate.boundSetOps("nameSet").add("李四");
        redisTemplate.boundSetOps("nameSet").add("王五");
    }

    /**
     * 根据 key 查询数据
     */
    @Test
    public void getValue(){
        Set set = redisTemplate.boundSetOps("nameSet").members();
        System.out.println(set);
    }

    /**
     * 删除值
     */
    @Test
    public void removeValue(){
        redisTemplate.boundSetOps("nameSet").remove("张三");
    }

    /**
     * 删除 Key
     */
    @Test
    public void deleteKey(){
        redisTemplate.delete("nameSet");
    }




}
