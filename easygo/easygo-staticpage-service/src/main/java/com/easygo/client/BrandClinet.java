package com.easygo.client;

import com.easygo.pojo.Brand;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @BelongsProject: easygo
 * @BelongsPackage: com.easygo.client
 * @Author: bruceliu
 * @QQ:1241488705
 * @CreateTime: 2020-04-14 10:29
 * @Description: TODO
 */
@FeignClient(value = "easygo-brand-service")
public interface BrandClinet {

    @RequestMapping("/brand_getBrandById")
    public Brand getBrandById(@RequestParam Integer id);
}
