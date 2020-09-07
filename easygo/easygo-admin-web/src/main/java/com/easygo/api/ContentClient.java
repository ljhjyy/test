package com.easygo.api;

import com.easygo.pojo.Content;
import com.easygo.utils.PageUtils;
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
 * @CreateTime: 2020-03-30 15:02
 * @Description: TODO
 */
@FeignClient(value = "easygo-ad-service")
public interface ContentClient {

    @RequestMapping("/content_getContentsByCategoryId")
    public List<Content> getContentsByCategoryId(@RequestParam Integer categoryId);

    @RequestMapping("content_update")
    public Integer updateContent(@RequestBody Content content);

    @RequestMapping("/content_getContentById")
    public Content getContentById(@RequestParam Integer id);

    @RequestMapping("/content_page")
    public PageUtils<Content> getContent_page(@RequestParam(defaultValue = "1",required = false) Integer pageIndex,
                                              @RequestParam(defaultValue = "5",required = false) Integer pageSize);


    @RequestMapping("/content_updateStatus")
    public Integer updateStatus(@RequestParam Integer id,@RequestParam Integer status);

    @RequestMapping("/content_add")
    public Integer addContent(@RequestBody Content content);
}
