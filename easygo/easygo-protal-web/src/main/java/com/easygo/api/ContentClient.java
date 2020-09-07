package com.easygo.api;

import com.easygo.pojo.Content;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @BelongsProject: easygo
 * @BelongsPackage: com.easygo.api
 * @CreateTime: 2020-03-30 16:12
 * @Description: TODO
 */
@FeignClient(value = "easygo-ad-service")
public interface ContentClient {

    @RequestMapping("/content_getContentsByCategoryId")
    public List<Content> getContentsByCategoryId(@RequestParam Integer categoryId);
}
