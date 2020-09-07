package com.easygo.controller;

import com.easygo.pojo.Users;
import com.easygo.service.UsersService;
import com.easygo.utils.MessageResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @BelongsProject: easygo
 * @BelongsPackage: com.easygo.controller
 * @Author: bruceliu
 * @QQ:1241488705
 * @CreateTime: 2020-04-16 14:58
 * @Description: TODO
 */
@RestController
@Scope("prototype")
public class UsersController {

    @Autowired
    UsersService usersService;

    /**
     * 用户注册
     * @param users
     * @return
     */
    @RequestMapping("/users_add")
    public Integer users_add(@RequestBody Users users){
        return usersService.registerUsers(users);
    }

}
