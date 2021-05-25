package com.redis.springdataredis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

/**
 * 测试 Redis 当中的 ZSet 类型
 * @author Cheng Qian on 2021/5/25 16:13
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Component
public class TestRedisZSet {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 语法：Boolean add(V value, double score)
     * 添加 value 到 ZSet 同时指定元素的分值
     */
    @Test
    public void addZSetValue(){
        redisTemplate.boundZSetOps("zSetKey").add("A", 1);
        redisTemplate.boundZSetOps("zSetKey").add("C", 3);
        redisTemplate.boundZSetOps("zSetKey").add("B", 2);
        redisTemplate.boundZSetOps("zSetKey").add("E", 5);
        redisTemplate.boundZSetOps("zSetKey").add("D", 4);
    }

    /**
     * 语法：Set<V> range(long start, long end)
     * 获取变量指定区间的 value
     * 通过索引区间返回有序集合成指定区间内的成员，其中有序集成员按分数值递增(从小到大)顺序排列
     */
    @Test
    public void rangeZSetValue(){
        // -1 表示最后一个成员
        Set zSetValues = redisTemplate.boundZSetOps("zSetKey").range(0, -1);
        System.out.println(zSetValues);
    }

    /**
     * 同上
     * 差别是：有序集成员按分数值递减(从大到小)顺序排列
     */
    @Test
    public void revrangeZSetValue(){
        // -1 表示最后一个成员
        Set zSetValues = redisTemplate.boundZSetOps("zSetKey").reverseRange(0, -1);
        System.out.println(zSetValues);
    }

    /**
     * 新增一个有序集合
     */
    @Test
    public void addSets(){
        ZSetOperations.TypedTuple<Object> objectTypedTuple1 = new DefaultTypedTuple<>("zset-3", 9.5);
        ZSetOperations.TypedTuple<Object> objectTypedTuple2 = new DefaultTypedTuple<>("zset-4", 9.9);

        Set<ZSetOperations.TypedTuple<Object>> tuples = new HashSet<>();
        tuples.add(objectTypedTuple1);
        tuples.add(objectTypedTuple2);

        System.out.println(redisTemplate.boundZSetOps("zset1").add(tuples));
        System.out.println(redisTemplate.boundZSetOps("zset1").range(0, -1));
    }

    /**
     * 从有序集合中移除一个或者多个元素
     */
    @Test
    public void removeMember(){
        System.out.println(redisTemplate.boundZSetOps("zset1").range(0, -1));
        System.out.println(redisTemplate.boundZSetOps("zset1").remove("zset-1"));
        System.out.println(redisTemplate.boundZSetOps("zset1").range(0, -1));
    }

    /**
     * 增加元素的score值，并返回增加后的值
     */
    @Test
    public void addMemberScore(){
        System.out.println(redisTemplate.boundZSetOps("zset1").incrementScore("zset-2", 1000));
    }

    /**
     * 返回有序集中指定成员的排名，其中有序集成员按分数值递增(从小到大)顺序排列
     * 排名从 0 开始，0 就是第一位
     */
    @Test
    public void getRank(){
        System.out.println(redisTemplate.boundZSetOps("zset1").range(0, -1));
        System.out.println(redisTemplate.boundZSetOps("zset1").rank("zset-2"));

        // reverseRank：按照有序集成员按分数值递减(从大到小)顺序排名
        //System.out.println(redisTemplate.boundZSetOps("zset1").reverseRank("zset-2"));
    }

    /**
     * 获取有序集合的成员数
     */
    @Test
    public void zcard(){
        System.out.println(redisTemplate.boundZSetOps("zset1").zCard());
    }

    /**
     * 移除指定索引位置的成员，其中有序集成员按分数值递增(从小到大)顺序排列
     */
    @Test
    public void removeMemberByRange(){
        System.out.println(redisTemplate.boundZSetOps("zset1").range(0, -1));
        redisTemplate.boundZSetOps("zset1").removeRange(0, 1);
        System.out.println(redisTemplate.boundZSetOps("zset1").range(0, -1));
    }

}
