package com.easygo.api;

import com.easygo.pojo.Goods;
import com.easygo.utils.PageUtils;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @BelongsProject: easygo
 * @BelongsPackage: com.easygo.api
 * @Author: bruceliu
 * @QQ:1241488705
 * @CreateTime: 2020-04-11 11:13
 * @Description: TODO
 */
@FeignClient(value = "easygo-search-service")
public interface GoodsSearchClient {

    /**
     * 01-搜索的接口
     * @param pageIndex
     * @param pageSize
     * @param keywords
     * @return
     */
    @RequestMapping("/goods_search")
    public PageUtils<Goods> goods_search(
            @RequestParam(value = "pageIndex",defaultValue = "1") Integer pageIndex,
            @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize,
            @RequestParam(value = "keywords",defaultValue = "") String keywords);
}
