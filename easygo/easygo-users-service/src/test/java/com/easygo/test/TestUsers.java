package com.easygo.test;

import com.easygo.pojo.Users;
import com.easygo.service.UsersService;
import org.apache.catalina.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @BelongsProject: easygo
 * @BelongsPackage: com.easygo.test
 * @Author: bruceliu
 * @QQ:1241488705
 * @CreateTime: 2020-04-16 15:00
 * @Description: TODO
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUsers {

    @Autowired
    UsersService usersService;

    @Test
    public void test1(){
        Users users=new Users();
        users.setUsername("波波");
        users.setPassword("admin123");
        users.setPhone("1237897979");
        users.setEmail("bobo@163.com");
        users.setNick_name("夜店小王子");
        users.setName("小波波");
        users.setQq("3453454");
        users.setSex("1");
        Integer count = usersService.registerUsers(users);
        System.out.println(count>0?"注册成功":"注册失败");
    }

}
