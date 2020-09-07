package com.easygo.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

/**
 * @BelongsProject: easygo
 * @BelongsPackage: com.easygo.test
 * @Author: bruceliu
 * @QQ:1241488705
 * @CreateTime: 2020-04-08 16:04
 * @Description: TODO
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestZset {

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Test
    public void testAdd(){
        redisTemplate.boundZSetOps("girls").add("西施",1);
        redisTemplate.boundZSetOps("girls").add("昭君",2);
        redisTemplate.boundZSetOps("girls").add("贵妃",3);
        redisTemplate.boundZSetOps("girls").add("芙蓉",5);
        redisTemplate.boundZSetOps("girls").add("凤姐",4);
        System.out.println("存值");
    }

    @Test
    public void testGet(){
        Set<Object> girls = redisTemplate.boundZSetOps("girls").range(0, -1);
        System.out.println(girls);
    }

    @Test
    public void testDelete(){
        redisTemplate.boundZSetOps("girls").remove("芙蓉","凤姐");
    }

    /**
     * 获取排名
     */
    @Test
    public void testGetpaiming(){
        Long rank = redisTemplate.boundZSetOps("girls").rank("贵妃");
        System.out.println(rank);
    }

    @Test
    public void testCount(){
        Long count = redisTemplate.boundZSetOps("girls").zCard();
        System.out.println("集合大小是:"+count);
    }

}
