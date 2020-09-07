package com.easygo.service.impl;

import com.easygo.mapper.UsersMapper;
import com.easygo.pojo.Users;
import com.easygo.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @BelongsProject: easygo
 * @BelongsPackage: com.easygo.service.impl
 * @Author: bruceliu
 * @QQ:1241488705
 * @CreateTime: 2020-04-16 14:57
 * @Description: TODO
 */
@Service
public class UsersServiceImpl implements UsersService {

    @Resource
    UsersMapper usersMapper;

    @Override
    public Integer registerUsers(Users users) {
        return usersMapper.registerUsers(users);
    }
}
