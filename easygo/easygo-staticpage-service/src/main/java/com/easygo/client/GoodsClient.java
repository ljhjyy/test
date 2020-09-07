package com.easygo.client;

import com.easygo.pojo.Goods;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @BelongsProject: easygo
 * @BelongsPackage: com.easygo.client
 * @Author: bruceliu
 * @QQ:1241488705
 * @CreateTime: 2020-04-13 16:32
 * @Description: TODO
 */
@FeignClient(value = "easygo-goods-service")
public interface GoodsClient {

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    @RequestMapping("/goods_getGoodsById")
    public Goods getGoodsById(@RequestParam("id") Long id);
}
