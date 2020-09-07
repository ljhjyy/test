package com.easygo.api;

import com.easygo.pojo.ItemCat;
import com.easygo.utils.PageUtils;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @BelongsProject: easygo
 * @BelongsPackage: com.easygo.api
 * @Author: bruceliu
 * @QQ:1241488705
 * @CreateTime: 2020-04-07 10:14
 * @Description: TODO
 */
@FeignClient(value = "easygo-itemcat-service")
public interface ItemCatClient {

    /**
     * 01-商品分类的接口
     * @param pageIndex
     * @param pageSize
     * @param parent_id
     * @return
     */
    @RequestMapping("/itemcat_page")
    public PageUtils<ItemCat> itemcat_page(@RequestParam(value = "pageIndex",defaultValue = "1") Integer pageIndex,
                                           @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
                                           @RequestParam(value = "parent_id",defaultValue = "0") Integer parent_id);

    /**
     * 根据ID查询一个分类对象
     * @param id
     * @return
     */
    @RequestMapping("/itemcat_getById")
    public ItemCat getItemCatById(@RequestParam Integer id);

    //新增
    @RequestMapping("/addItemCat")
    public Integer addItemCat(@RequestBody ItemCat itemCat);
}
