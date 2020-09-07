package com.easygo.controller;

import com.easygo.pojo.Order;
import com.easygo.pojo.OrderInfo;
import com.easygo.pojo.OrderItem;
import com.easygo.service.OrderService;
import com.easygo.utils.IdWorker;
import com.easygo.utils.MessageResults;
import com.easygo.utils.TimeUtls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @BelongsProject: easygo
 * @BelongsPackage: com.easygo.controller
 * @Author: bruceliu
 * @QQ:1241488705
 * @CreateTime: 2020-04-24 14:40
 * @Description: 测试分布式事务
 */
@Controller
@Scope("prototype")
public class XianDanController {

    @Autowired
    OrderService orderService;

    /**
     * 下单 测试分布式事务
     * @return
     */
    @RequestMapping("/xiaDan")
    @ResponseBody
    public MessageResults testXiaDan(){
        try {
            OrderInfo orderInfo=new OrderInfo();

            //模拟一个订单数据对象，原本应该从订单详情页面获取数据的！此处在模拟数据！
            Order order=new Order();
            order.setOrder_id(new IdWorker().nextId());
            order.setUser_id("bruce");
            order.setPayment_type("1");
            order.setStatus("1");
            order.setCreate_time(TimeUtls.getNow());
            order.setUpdate_time(TimeUtls.getNow());
            order.setReceiver_area_name("北京XXX洗浴中心");
            order.setReceiver_mobile("13366789010");
            order.setReceiver("bruce");
            order.setSource_type("1");
            order.setPayment(2.0);

            //订单详情数据，模拟
            List<OrderItem> orderItems=new ArrayList<OrderItem>();
            OrderItem orderItem=new OrderItem();
            orderItem.setItem_id(1369322L); //订单详情中sku商品ID  目前库存56
            orderItem.setGoods_id(149187842868051L);
            orderItem.setOrder_id(order.getOrder_id());
            orderItem.setTitle("大牧汗精制肥牛片540g 原切谷饲牛肉 北京");
            orderItem.setPrice(80.00);
            orderItem.setNum(2L); //表示买2件
            orderItem.setTotal_fee(orderItem.getNum()*orderItem.getPrice());
            orderItem.setPic_path("http://192.168.6.130:8866/group1/M00/00/00/wKgGglw950qAWTsOAACXPpn4lRU540.png");
            orderItem.setSeller_id("bruce");
            orderItems.add(orderItem);

            orderInfo.setOrder(order);
            orderInfo.setOrderItems(orderItems);

            System.out.println(orderInfo);
            System.out.println(orderItems);

            int xiadan = orderService.xiadan(orderInfo, orderItems);
            System.out.println("下单："+xiadan);

            return new MessageResults(200,"下单成功");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return new MessageResults(500,"下单异常");
    }


}
