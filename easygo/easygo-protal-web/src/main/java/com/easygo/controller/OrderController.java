package com.easygo.controller;

import com.easygo.api.OrderClient;
import com.easygo.pojo.CartBean;
import com.easygo.pojo.Order;
import com.easygo.pojo.OrderInfo;
import com.easygo.pojo.OrderItem;
import com.easygo.utils.IdWorker;
import com.easygo.utils.MessageResults;
import com.easygo.utils.TimeUtls;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @BelongsProject: easygo
 * @BelongsPackage: com.easygo.controller
 * @Author: bruceliu
 * @QQ:1241488705
 * @CreateTime: 2020-04-22 10:21
 * @Description: TODO
 */
@Controller
@Scope("prototype")
public class OrderController {

    @Autowired
    OrderClient orderClient;

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    /**
     * 01-新增订单
     * @return
     */
    @RequestMapping("/order_add")
    @ResponseBody
    public MessageResults addOrder(Order order){
        System.out.println("order:"+order);
        try {
            //order对象页面表单中藏的一些值
            MessageResults results=null;
            String loginName = SecurityUtils.getSubject().getPrincipal().toString();
            System.out.println("当前登录人:"+loginName);
            //从redis中获取购物车数据
            //从该用户的redis中获取原来的购物车数据集合
            redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
            List<CartBean> mycarts = (ArrayList<CartBean>)redisTemplate.opsForValue().get(loginName);//key redis中的key  用户名不重复

            //订单ID：订单ID在系统中不能重复的，这里就使用雪花算法
            IdWorker idWorker=new IdWorker();
            order.setOrder_id(idWorker.nextId());
            order.setUser_id(loginName);
            order.setStatus("1"); //未支付
            order.setCreate_time(TimeUtls.getNow());
            order.setUpdate_time(TimeUtls.getNow());
            order.setSource_type("2"); //PC
            //订单金额等在后台运算一下
            double money=0;
            for(CartBean cartBean:mycarts){
                cartBean.setOrder_id(order.getOrder_id());//外键
                cartBean.setSeller_id(order.getSeller_id());
                money=money+cartBean.getNum()*cartBean.getPrice();
            }
            //订单金额
            order.setPayment(money);

            List<OrderItem> orderItems=new ArrayList<OrderItem>();
            for (CartBean cart : mycarts) {
                OrderItem orderItem=new OrderItem();
                orderItem.setTitle(cart.getTitle());
                orderItem.setSeller_id(cart.getSeller_id());
                orderItem.setTotal_fee(cart.getTotal_fee());
                orderItem.setNum(cart.getNum());
                orderItem.setPic_path(cart.getPic_path());
                orderItem.setGoods_id(cart.getGoods_id());
                orderItem.setPrice(cart.getPrice());
                orderItem.setItem_id(cart.getItem_id());
                orderItem.setOrder_id(cart.getOrder_id());
                orderItems.add(orderItem);
            }

            //封装新增的对象
            OrderInfo orderInfo=new OrderInfo();
            orderInfo.setOrder(order);
            orderInfo.setOrderItems(orderItems);

            System.out.println("orderInfo:"+orderInfo);

            Integer count = orderClient.addOrders(orderInfo);
            System.out.println(count);
            if(count>0){
                return new MessageResults(200,"下单成功");
            }else{
                return new MessageResults(500,"下单异常");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return new MessageResults(500,"下单异常");
    }
}
