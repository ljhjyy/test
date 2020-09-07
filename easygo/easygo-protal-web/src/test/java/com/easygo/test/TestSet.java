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
 * @CreateTime: 2020-04-08 15:13
 * @Description: TODO
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestSet {

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Test
    public void setValue(){
        redisTemplate.boundSetOps("girls").add("小苍");
        redisTemplate.boundSetOps("girls").add("小泽");
        redisTemplate.boundSetOps("girls").add("龙泽");
        System.out.println("存值...");
    }

    @Test
    public void testValue(){
        Set<Object> girls = redisTemplate.boundSetOps("girls").members();
        for (Object girl : girls) {
            System.out.println(girl);
        }
    }

    @Test
    public void testRemove(){
        redisTemplate.boundSetOps("girls").remove("小苍");
    }

    @Test
    public void testDelete(){
        redisTemplate.delete("girls");
    }


}
