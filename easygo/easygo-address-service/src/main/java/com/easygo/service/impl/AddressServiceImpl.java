package com.easygo.service.impl;

import com.easygo.mapper.AddressMapper;
import com.easygo.pojo.Address;
import com.easygo.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @BelongsProject: easygo
 * @BelongsPackage: com.easygo.service.impl
 * @Author: bruceliu
 * @QQ:1241488705
 * @CreateTime: 2020-04-21 15:02
 * @Description: TODO
 */
@Service
public class AddressServiceImpl implements AddressService {

    @Resource
    AddressMapper addressMapper;

    @Override
    public List<Address> getMyAddress(String userName) {
        return addressMapper.getMyAddress(userName);
    }

    @Override
    public Address getMyDeafultAddress(String userName) {
        return addressMapper.getMyDeafultAddress(userName);
    }

    @Override
    public Address getAddressById(Long id) {
        return addressMapper.getAddressById(id);
    }
}
