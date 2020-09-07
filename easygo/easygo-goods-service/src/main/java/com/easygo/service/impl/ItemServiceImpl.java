package com.easygo.service.impl;

import com.codingapi.tx.annotation.ITxTransaction;
import com.easygo.mapper.ItemMapper;
import com.easygo.pojo.Item;
import com.easygo.pojo.OrderItem;
import com.easygo.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @BelongsProject: easygo
 * @BelongsPackage: com.easygo.service.impl
 * @Author: bruceliu
 * @QQ:1241488705
 * @CreateTime: 2020-04-14 14:48
 * @Description: TODO
 */
/**
 * 注意需要实现 ITxTransaction；
 */
@Service
public class ItemServiceImpl implements ItemService, ITxTransaction {

    @Resource
    ItemMapper itemMapper;

    @Override
    public List<Item> getItemsByGoodsId(Long goodsId) {
        return itemMapper.getItemsByGoodsId(goodsId);
    }

    @Override
    public Item getItemById(Long itemId) {
        return itemMapper.getItemById(itemId);
    }

    /**
     * 01-这个方法就是修改库存的方法，这个方法是不是就是事务的参数与者
     * @param orderItems
     * @return
     */
    @Transactional
    @Override
    public int updateItemNum(List<OrderItem> orderItems) {
        System.out.println("要更新的orderItems集合："+orderItems);
        for (OrderItem orderItem : orderItems) {
            int count = itemMapper.updateItemNum(orderItem);
            System.out.println("更新条数:"+count);
        }
        return 1;
    }


}
