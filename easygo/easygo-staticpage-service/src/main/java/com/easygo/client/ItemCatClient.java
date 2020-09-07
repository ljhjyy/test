package com.easygo.client;

import com.easygo.pojo.ItemCat;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @BelongsProject: easygo
 * @BelongsPackage: com.easygo.client
 * @Author: bruceliu
 * @QQ:1241488705
 * @CreateTime: 2020-04-14 11:00
 * @Description: TODO
 */
@FeignClient(value = "easygo-itemcat-service")
public interface ItemCatClient {

    /**
     * 根据ID查询一个分类对象
     * @param id
     * @return
     */
    @RequestMapping("/itemcat_getById")
    public ItemCat getItemCatById(@RequestParam Integer id);
}
