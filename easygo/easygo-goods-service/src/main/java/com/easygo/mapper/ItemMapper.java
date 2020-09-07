package com.easygo.mapper;

import com.easygo.pojo.Item;
import com.easygo.pojo.OrderItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @BelongsProject: easygo
 * @BelongsPackage: com.easygo.mapper
 * @Author: bruceliu
 * @QQ:1241488705
 * @CreateTime: 2020-04-14 14:44
 * @Description: TODO
 */
@Mapper
public interface ItemMapper {

    //查询商品的所有SKU对象
    public List<Item> getItemsByGoodsId(Long goodsId);

    //根据ID查询对象
    public Item getItemById(Long itemId);

    //更新库存
    public int updateItemNum(OrderItem orderItem);

}
