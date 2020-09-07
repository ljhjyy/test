package com.easygo.controller;

import com.easygo.pojo.Item;
import com.easygo.pojo.OrderInfo;
import com.easygo.pojo.OrderItem;
import com.easygo.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @BelongsProject: easygo
 * @BelongsPackage: com.easygo.controller
 * @Author: bruceliu
 * @QQ:1241488705
 * @CreateTime: 2020-04-14 14:49
 * @Description: TODO
 */
@RestController
@Scope("prototype")
public class ItemController {

    @Autowired
    ItemService itemService;

    //查询商品的所有SKU对象
    @RequestMapping("/items_getItemsByGoodsId")
    public List<Item> getItemsByGoodsId(@RequestParam("goodsId") Long goodsId){
        return itemService.getItemsByGoodsId(goodsId);
    }

    //根据ID查询对象
    @RequestMapping("/item_getbyId")
    public Item getItemById(@RequestParam("itemId") Long itemId){
        return itemService.getItemById(itemId);
    }

    //更新库存
    @PostMapping("/item_updateItemNum")
    public Integer updateItemNum(@RequestBody List<OrderItem> orderItems){
        return itemService.updateItemNum(orderItems);
    }
}
