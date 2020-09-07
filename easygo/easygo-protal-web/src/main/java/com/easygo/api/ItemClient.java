package com.easygo.api;

import com.easygo.pojo.Item;
import com.easygo.pojo.OrderInfo;
import com.easygo.pojo.OrderItem;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @BelongsProject: easygo
 * @BelongsPackage: com.easygo.api
 * @Author: bruceliu
 * @QQ:1241488705
 * @CreateTime: 2020-04-20 16:12
 * @Description: TODO
 */
@FeignClient(value = "easygo-goods-service")
public interface ItemClient {

    @RequestMapping("/item_getbyId")
    public Item getItemById(@RequestParam("itemId") Long itemId);

    //更新库存
    @RequestMapping("/item_updateItemNum")
    public Integer updateItemNum(@RequestBody List<OrderItem> orderItems);


}
