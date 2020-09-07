package com.easygo.service;

import com.easygo.pojo.Address;

import java.util.List;

/**
 * @BelongsProject: easygo
 * @BelongsPackage: com.easygo.service
 * @Author: bruceliu
 * @QQ:1241488705
 * @CreateTime: 2020-04-21 15:02
 * @Description: TODO
 */
public interface AddressService {

    List<Address> getMyAddress(String userName);

    public Address getMyDeafultAddress(String userName);

    public Address getAddressById(Long id);
}
