package com.easygo.service.impl;

import com.codingapi.tx.annotation.ITxTransaction;
import com.easygo.mapper.OrderMapper;
import com.easygo.pojo.Order;
import com.easygo.pojo.OrderInfo;
import com.easygo.pojo.OrderItem;
import com.easygo.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @BelongsProject: easygo
 * @BelongsPackage: com.easygo.service.impl
 * @Author: bruceliu
 * @QQ:1241488705
 * @CreateTime: 2020-04-22 10:06
 * @Description: TODO
 */
@Service
public class OrderServiceImpl implements OrderService, ITxTransaction {

    @Resource
    OrderMapper orderMapper;

    /**
     * 事务操作：事务的参与方
     * @param orderInfo
     * @return
     */
    @Transactional
    @Override
    public int addOrders(OrderInfo orderInfo) {
        orderMapper.addOrder(orderInfo.getOrder());
        List<OrderItem> orderItems = orderInfo.getOrderItems();
        //System.out.println(100/0); 太二了 自己加了一异常 回滚！！！
        for (OrderItem orderItem : orderItems) {
            orderMapper.addOrderItem(orderItem);
        }
        return 1;
    }
}
