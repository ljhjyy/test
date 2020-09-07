package com.easygo.api;

import com.easygo.pojo.ContentCategory;
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
 * @CreateTime: 2020-03-30 10:51
 * @Description: TODO
 */
@FeignClient(value = "easygo-ad-service")
public interface ContentCategoryClient {

    /**
     * 查询有效的广告分类
     * @return
     */
    @RequestMapping("/contentCategory_list")
    public List<ContentCategory> getContentCategorys();

    @RequestMapping("/contentCategory_delete")
    public int deleteContentCategory(@RequestParam Integer id);

    @RequestMapping("/contentCategory_update")
    public int updateContentCategory(@RequestBody ContentCategory contentCategory);

    @RequestMapping("/contentCategory_getById")
    public ContentCategory getById(@RequestParam Integer id);

    @RequestMapping("/contentCategory_add")
    public int addContentCategory(@RequestBody ContentCategory contentCategory);

    @RequestMapping("/contentCategory_page")
    public PageUtils<ContentCategory> getcontentCategorys(@RequestParam(defaultValue = "1",required = false) Integer pageIndex, @RequestParam(defaultValue = "5",required = false) Integer pageSize,
                                                          @RequestParam(defaultValue = "",required = false) String name);
}
