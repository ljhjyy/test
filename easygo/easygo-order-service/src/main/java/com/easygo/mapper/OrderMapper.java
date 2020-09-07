package com.easygo.mapper;

import com.easygo.pojo.Order;
import com.easygo.pojo.OrderItem;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @BelongsProject: easygo
 * @BelongsPackage: com.easygo.mapper
 * @Author: bruceliu
 * @QQ:1241488705
 * @CreateTime: 2020-04-19 21:39
 * @Description: TODO
 */
@Mapper
public interface OrderMapper {

    /**
     * 新增订单  订单表 tb_order
     */
    public Integer addOrder(Order order);

    /**
     *  新增订单详情  tb_order_item
     */
    public Integer addOrderItem(OrderItem orderItem);
}
