package com.easygo.service;

import com.easygo.pojo.Item;
import com.easygo.pojo.OrderItem;

import java.util.List;

/**
 * @BelongsProject: easygo
 * @BelongsPackage: com.easygo.service
 * @Author: bruceliu
 * @QQ:1241488705
 * @CreateTime: 2020-04-14 14:47
 * @Description: TODO
 */
public interface ItemService {

    //查询商品的所有SKU对象
    public List<Item> getItemsByGoodsId(Long goodsId);

    //根据ID查询对象
    public Item getItemById(Long itemId);

    //更新数据量
    public int updateItemNum(List<OrderItem> orderItems);
}
