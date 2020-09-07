package com.easygo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.TimeUnit;

/**
 * @BelongsProject: easygo
 * @BelongsPackage: com.easygo.controller
 * @Author: bruceliu
 * @QQ:1241488705
 * @CreateTime: 2020-04-24 16:44
 * @Description: 测试
 */
@Controller
@Scope("prototype")
public class TimeController {

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    /**
     * 测试key的有效期
     * @return
     */
    @RequestMapping("/ttl")
    @ResponseBody
    public String testTimeTTL(){
        Long leftTime = redisTemplate.getExpire("message", TimeUnit.SECONDS);
        String s="message这个key的有效期："+leftTime+"秒!";
        System.out.println(s);
        return s;
    }
}
