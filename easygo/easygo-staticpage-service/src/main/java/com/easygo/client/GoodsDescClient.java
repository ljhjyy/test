package com.easygo.client;

import com.easygo.pojo.GoodsDesc;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @BelongsProject: easygo
 * @BelongsPackage: com.easygo.client
 * @Author: bruceliu
 * @QQ:1241488705
 * @CreateTime: 2020-04-14 09:48
 * @Description: TODO
 */
@FeignClient(value = "easygo-goods-service")
public interface GoodsDescClient {

    @RequestMapping("goodsDesc_getById")
    public GoodsDesc getGoodsDescById(@RequestParam("id") Long id);
}
