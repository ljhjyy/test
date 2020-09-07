package com.easygo.api;

import com.easygo.pojo.Address;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @BelongsProject: easygo
 * @BelongsPackage: com.easygo.api
 * @Author: bruceliu
 * @QQ:1241488705
 * @CreateTime: 2020-04-21 15:08
 * @Description: TODO
 */
@FeignClient(value = "easygo-address-service")
public interface AddressClient {

    @RequestMapping("/address_getMyAddress")
    List<Address> getMyAddress(@RequestParam("userName") String userName);

    @RequestMapping("/address_getMyDeafultAddress")
    public Address getMyDeafultAddress(@RequestParam("userName")  String userName);

    @RequestMapping("/address_getAddressById")
    public Address getAddressById(@RequestParam("id")  Long id);
}
