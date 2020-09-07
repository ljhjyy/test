package com.easygo.api;

import com.easygo.pojo.ItemCat;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @BelongsProject: easygo
 * @BelongsPackage: com.easygo.api
 * @Author: bruceliu
 * @QQ:1241488705
 * @CreateTime: 2020-04-07 14:51
 * @Description: TODO
 */
@FeignClient(value="easygo-itemcat-service")
public interface ItemCatClient {

    @RequestMapping("/itemcat_all")
    public List<ItemCat> getItemCats();
}
