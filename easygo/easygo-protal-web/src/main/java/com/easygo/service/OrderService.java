package com.easygo.service;

import com.easygo.pojo.OrderInfo;
import com.easygo.pojo.OrderItem;

import java.util.List;

/**
 * @BelongsProject: easygo
 * @BelongsPackage: com.easygo.service
 * @Author: bruceliu
 * @QQ:1241488705
 * @CreateTime: 2020-04-24 11:38
 * @Description: TODO
 */
public interface OrderService {

    /**
     * 事务发起方  orderInfo 要新增的订单
     *            orderItems 要更新的库存对象
     * @param orderInfo
     * @param orderItems
     * @return
     */
    public int xiadan(OrderInfo orderInfo, List<OrderItem> orderItems);

}
