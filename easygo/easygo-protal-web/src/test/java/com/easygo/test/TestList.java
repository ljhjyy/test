package com.easygo.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @BelongsProject: easygo
 * @BelongsPackage: com.easygo.test
 * @Author: bruceliu
 * @QQ:1241488705
 * @CreateTime: 2020-04-08 15:23
 * @Description: TODO
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestList {

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    /**
     * 右压栈
     */
    @Test
    public void testSetValue(){
        redisTemplate.boundListOps("hobbys").rightPush("美女");
        redisTemplate.boundListOps("hobbys").rightPush("别墅");
        redisTemplate.boundListOps("hobbys").rightPush("汽车");
    }

    @Test
    public void testGet(){
        List<Object> hobbys = redisTemplate.boundListOps("hobbys").range(0, 10);
        System.out.println(hobbys);
    }

    @Test
    public void testDelete(){
        redisTemplate.boundListOps("hobbys").remove(1,"汽车");
    }

    @Test
    public void testDeleteALL(){
        redisTemplate.delete("hobbys");
    }
}
