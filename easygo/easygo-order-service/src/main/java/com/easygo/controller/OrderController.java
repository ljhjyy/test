package com.easygo.controller;

import com.easygo.pojo.OrderInfo;
import com.easygo.service.OrderService;
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
 * @CreateTime: 2020-04-22 10:16
 * @Description: TODO
 */
@RestController
@Scope("prototype")
public class OrderController {

    @Autowired
    OrderService orderService;

    /**
     * 01-新增订单
     * @param orderInfo
     * @return
     */
    @RequestMapping("/order_add")
    public Integer addOrders(@RequestBody OrderInfo orderInfo){
        System.out.println("服务端:"+orderInfo);
        return orderService.addOrders(orderInfo);
    }

}
