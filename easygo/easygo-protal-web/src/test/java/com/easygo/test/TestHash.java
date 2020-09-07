package com.easygo.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Set;

/**
 * @BelongsProject: easygo
 * @BelongsPackage: com.easygo.test
 * @Author: bruceliu
 * @QQ:1241488705
 * @CreateTime: 2020-04-08 15:52
 * @Description: TODO
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestHash {

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Test
    public void testSetValue(){
        redisTemplate.boundHashOps("names").put("a","宋小宝1");
        redisTemplate.boundHashOps("names").put("b","宋小宝2");
        redisTemplate.boundHashOps("names").put("c","宋小宝3");
        redisTemplate.boundHashOps("names").put("d","宋小宝4");
        redisTemplate.boundHashOps("names").put("e","宋小宝5");
        System.out.println("存值");
    }
    
    @Test
    public void testgetValue(){
        Set<Object> keys = redisTemplate.boundHashOps("names").keys();
        System.out.println(keys);
    }

    @Test
    public void testgetValues(){
        List<Object> names = redisTemplate.boundHashOps("names").values();
        System.out.println(names);
    }

    @Test
    public void testgetNameByKey(){
        String o = (String)redisTemplate.boundHashOps("names").get("e");
        System.out.println(o);
    }

    @Test
    public void testRemove(){
        redisTemplate.boundHashOps("names").delete("e");
        System.out.println("删除成功");
    }

}
