package com.easygo.service.impl;

import com.codingapi.tx.annotation.TxTransaction;
import com.easygo.api.ItemClient;
import com.easygo.api.OrderClient;
import com.easygo.pojo.OrderInfo;
import com.easygo.pojo.OrderItem;
import com.easygo.service.OrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @BelongsProject: easygo
 * @BelongsPackage: com.easygo.service.impl
 * @Author: bruceliu
 * @QQ:1241488705
 * @CreateTime: 2020-04-24 11:40
 * @Description: TODO
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    OrderClient orderClient;

    @Resource
    ItemClient itemClient;

    /**
     * 事务的发起方
     * @param orderInfo
     * @param orderItems
     * @return
     */
    @TxTransaction(isStart = true)
    @Override
    public int xiadan(OrderInfo orderInfo, List<OrderItem> orderItems) {

        //数据源不是同一个
        Integer count1 = orderClient.addOrders(orderInfo);
        System.out.println("调用订单服务,新增订单!"+count1);

        //模拟有异常
        //System.out.println(100/0);

        //数据源不是同一个
        int count2 = itemClient.updateItemNum(orderItems);
        System.out.println("调用商品服务，修改库存！"+count2);

        return count1+count2;
    }
}
