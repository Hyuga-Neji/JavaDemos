package com.redis.springdataredis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * 测试 Redis 当中的 List 类型
 * @author Cheng Qian on 2021/5/25 15:28
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Component
public class TestRedisList {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 右压栈方式
     * 后添加的值，放在最 “后” 边
     */
    @Test
    public void setValueOnRight(){
//        redisTemplate.boundListOps("nameList_right").rightPush("刘备");
//        redisTemplate.boundListOps("nameList_right").rightPush("张飞");
//        redisTemplate.boundListOps("nameList_right").rightPush("关羽");
    }

    /**
     * 获取右压栈的值
     */
    @Test
    public void getValueOnRight(){
        List list = redisTemplate.boundListOps("nameList_right").range(0, 10);
        System.out.println(list);
    }

    /**
     * 删除右压栈的 Key
     */
    @Test
    public void delete(){
        redisTemplate.delete("nameList_right");
    }

    /**
     * 左压栈方式
     * 后添加的值，放在最 “前” 边
     */
    @Test
    public void setValueOnLeft(){
        redisTemplate.boundListOps("nameList_left").leftPush("刘备");
        redisTemplate.boundListOps("nameList_left").leftPush("张飞");
        redisTemplate.boundListOps("nameList_left").leftPush("关羽");
    }

    /**
     * 获取左压栈的值
     */
    @Test
    public void getValueOnLeft(){
        List list = redisTemplate.boundListOps("nameList_left").range(0, 10);
        System.out.println(list);
    }

    /**
     * 删除左压栈的值
     */
    @Test
    public void removeValue(){
        redisTemplate.boundListOps("nameList_left").remove(0, "张飞");
    }




}
