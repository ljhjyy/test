package com.easygo.client;

import com.easygo.pojo.Item;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @BelongsProject: easygo
 * @BelongsPackage: com.easygo.client
 * @Author: bruceliu
 * @QQ:1241488705
 * @CreateTime: 2020-04-14 14:51
 * @Description: TODO
 */
@FeignClient(value = "easygo-goods-service")
public interface ItemClient {

    //查询商品的所有SKU对象
    @RequestMapping("/items_getItemsByGoodsId")
    public List<Item> getItemsByGoodsId(@RequestParam("goodsId") Long goodsId);
}
