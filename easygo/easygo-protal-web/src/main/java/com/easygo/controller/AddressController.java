package com.easygo.controller;

import com.easygo.api.AddressClient;
import com.easygo.pojo.Address;
import com.easygo.pojo.CartBean;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @BelongsProject: easygo
 * @BelongsPackage: com.easygo.controller
 * @Author: bruceliu
 * @QQ:1241488705
 * @CreateTime: 2020-04-21 15:10
 * @Description: TODO
 */
@Controller
@Scope("prototype")
public class AddressController {

    @Autowired
    AddressClient addressClient;

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    /**
     * 01-收获地址选择,如果进来的话，那么一定试试登录状态
     * @return
     */
    @RequestMapping("/address_choose")
    public String addressChoose(Model model){
        Subject subject = SecurityUtils.getSubject();
        String loginName = subject.getPrincipal().toString();
        System.out.println("当前登录用户是:"+loginName);
        //获取用户的地址列表
        List<Address> addresses = addressClient.getMyAddress(loginName);
        //获取默认的地址
        Address address = addressClient.getMyDeafultAddress(loginName);
        //获取redis中用户的购物车数据，页面显示 以供用户确认，送货清单
        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        List<CartBean> mycarts = (ArrayList<CartBean>)redisTemplate.opsForValue().get(loginName);

        Long totalCount=0L; //商品总数目
        Double totalMoney=0.0; //总金额
        //后台运算一下金额
        for (CartBean mycart : mycarts) {
            totalCount=totalCount+mycart.getNum();
            totalMoney=totalMoney+(mycart.getNum()*mycart.getPrice());
        }

        model.addAttribute("addresses",addresses);
        //默认邮寄的地址，表单藏默认值的时候使用
        model.addAttribute("add",address);
        model.addAttribute("totalCount",totalCount);
        model.addAttribute("totalMoney",totalMoney);
        model.addAttribute("mycarts",mycarts);

        System.out.println(addresses);
        System.out.println(totalCount);
        System.out.println(totalMoney);
        System.out.println(mycarts);

        return "orderInfo";
    }

    /**
     * 根据ID查询对象
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/getAddresById")
    public Address getAddresById(Long id){
        return addressClient.getAddressById(id);
    }
}
