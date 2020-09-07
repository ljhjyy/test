package com.easygo.test;

import com.easygo.pojo.Admin;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @BelongsProject: easygo
 * @BelongsPackage: com.easygo.test
 * @Author: bruceliu
 * @QQ:1241488705
 * @CreateTime: 2020-04-08 11:32
 * @Description: TODO
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRedis {

    /**
     * 操作Redis的最核心对象 需要使用泛型
     * redisTemplate可以操作Redis的场景的五种数据类型
     */
    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    /**
     * 新增Redis并设置Redis的key的过期时间
     */
    @Test
    public void testSetValueWithTimeOut(){
        //key的过期时间是10秒种自动失效
        redisTemplate.opsForValue().set("message","各位老司机，开往红浪漫的车已经发车...",50L, TimeUnit.SECONDS);
        System.out.println("设置完成");
    }



    /**
     * 01-向Redis中存储字符串
     */
    @Test
    public void testSet(){
        System.out.println(redisTemplate);
        redisTemplate.opsForValue().set("hobby","美女汽车钞票..");
        System.out.println("存值成功");
    }

    /**
     * 获取字符串
     */
    @Test
    public void testGet(){
        String username = (String)redisTemplate.opsForValue().get("hobby");
        System.out.println("redis中的值是:"+username);
    }

    /**
     * 存贮对象
     */
    @Test
    public void testSetAdmins(){
        Admin admin=new Admin();
        admin.setUsername("小波波");
        admin.setPassword("admin111");
        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        redisTemplate.opsForValue().set("admin",admin);
        System.out.println("存储成功~~");
    }

    /**
     * 获取对象
     */
    @Test
    public void testGetAdmins(){
        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        Admin admin=(Admin) redisTemplate.opsForValue().get("admin");
        System.out.println("获取出来:"+admin);
    }


    /**
     * 存贮Json
     */
    @Test
    public void testSetAdmins1(){
        Admin admin=new Admin();
        admin.setUsername("小波波");
        admin.setPassword("夜店小王子");
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(Admin.class));
        redisTemplate.opsForValue().set("admin1",admin);
    }

    @Test
    public void testGetAdmin1(){
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(Admin.class));
        Admin admin=(Admin) redisTemplate.opsForValue().get("admin1");
        System.out.println(admin);
    }


}
