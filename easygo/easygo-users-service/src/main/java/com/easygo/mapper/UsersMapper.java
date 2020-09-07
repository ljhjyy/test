package com.easygo.mapper;

import com.easygo.pojo.Users;
import org.apache.ibatis.annotations.Mapper;

/**
 * @BelongsProject: easygo
 * @BelongsPackage: com.easygo.mapper
 * @Author: bruceliu
 * @QQ:1241488705
 * @CreateTime: 2020-04-16 14:49
 * @Description: TODO
 */
@Mapper
public interface UsersMapper {

    public Integer registerUsers(Users users);
}
