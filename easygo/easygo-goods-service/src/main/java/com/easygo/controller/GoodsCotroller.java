package com.easygo.controller;

import com.easygo.pojo.Goods;
import com.easygo.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @BelongsProject: easygo
 * @BelongsPackage: com.easygo.controller
 * @Author: bruceliu
 * @QQ:1241488705
 * @CreateTime: 2020-04-10 16:29
 * @Description: TODO
 */
@RestController
@Scope("prototype")
public class GoodsCotroller {

    @Autowired
    GoodsService goodsService;

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    @RequestMapping("/goods_getGoodsById")
    public Goods getGoodsById(@RequestParam("id") Long id){
        return goodsService.getGoodsById(id);
    }

    /*
     * 查询所有的上架的商品数据
     */
    @RequestMapping("/goods_getGoods")
    public List<Goods> getGoods(@RequestParam Integer is_marketable){
        return  goodsService.getGoods(is_marketable);
    }
}
