package com.easygo.api;

import com.easygo.pojo.OrderInfo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @BelongsProject: easygo
 * @BelongsPackage: com.easygo.api
 * @Author: bruceliu
 * @QQ:1241488705
 * @CreateTime: 2020-04-22 10:20
 * @Description: TODO
 */
@FeignClient(value = "easygo-order-service")
public interface OrderClient {

    /**
     * 01-新增订单
     * @param orderInfo
     * @return
     */
    @RequestMapping("/order_add")
    public Integer addOrders(@RequestBody OrderInfo orderInfo);
}
