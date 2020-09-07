package com.easygo.client;

import com.easygo.pojo.Goods;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @BelongsProject: easygo
 * @BelongsPackage: com.easygo.client
 * @Author: bruceliu
 * @QQ:1241488705
 * @CreateTime: 2020-04-10 16:37
 * @Description: TODO
 */
@FeignClient(value = "easygo-goods-service")
public interface GoodsClient {

    /*
     * 查询所有的上架的商品数据
     */
    @RequestMapping("/goods_getGoods")
    public List<Goods> getGoods(@RequestParam Integer is_marketable);
}
