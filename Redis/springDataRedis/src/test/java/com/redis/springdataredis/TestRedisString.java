package com.redis.springdataredis;

import com.redis.springdataredis.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 测试 Redis 当中的 String 字符串类型
 * @author Cheng Qian on 2021/5/19 11:25
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Component
public class TestRedisString {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 添加一个字符串
     */
    @Test
    public void testStringSet(){
        this.redisTemplate.opsForValue().set("myKey", "myValue");
    }

    /**
     * 获取一个字符串
     */
    @Test
    public void testStringGet(){
        String value = (String) redisTemplate.opsForValue().get("myKey");
        System.out.println(value);
    }

    /**
     * 将对象序列化成字符串，存放在 Redis 中
     */
    @Test
    public void testStringUser(){
        User user = new User(1002, "张三", 24);
        redisTemplate.opsForValue().set("user4", user);
    }

    /**
     * 获取对象字符串
     */
    @Test
    public void getStringUser(){
        User user = (User) redisTemplate.opsForValue().get("user4");
        System.out.println(user);
    }

    /**
     * 将多个对象放进 List，然后将 List 序列化到 Redis中存起来
     */
    @Test
    public void setListUsers(){
        User user_1 = new User(1001, "张三", 24);
        User user_2 = new User(1002, "李四", 25);
        List<User> list = new ArrayList<>();
        list.add(user_1);
        list.add(user_2);
        redisTemplate.opsForValue().set("userList-1", list);
    }

    /**
     * 取出 List
     */
    @Test
    public void getListUsers(){
        List<User> list = (List<User>) redisTemplate.opsForValue().get("userList-1");
        assert list != null;
        System.out.println(list.size());
    }

}
