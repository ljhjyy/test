package com.easygo.mapper;

import com.easygo.pojo.Address;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @BelongsProject: easygo
 * @BelongsPackage: com.easygo.mapper
 * @Author: bruceliu
 * @QQ:1241488705
 * @CreateTime: 2020-04-21 14:53
 * @Description: TODO
 */
@Mapper
public interface AddressMapper {

    List<Address> getMyAddress(String userName);

    public Address getMyDeafultAddress(String userName);

    public Address getAddressById(Long id);
}
