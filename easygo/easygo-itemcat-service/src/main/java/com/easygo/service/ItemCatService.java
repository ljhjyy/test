package com.easygo.service;

import com.easygo.pojo.ItemCat;

import java.util.List;
import java.util.Map;

/**
 * @BelongsProject: easygo
 * @BelongsPackage: com.easygo.service
 * @Author: bruceliu
 * @QQ:1241488705
 * @CreateTime: 2020-04-07 10:05
 * @Description: TODO
 */
public interface ItemCatService {

    // parent_id=0 表示一级分类
    public Integer getTotalCount(Map<String,Object> map);

    public List<ItemCat> getItemCatsByPage(Map<String,Object> map);

    public ItemCat getItemCatById(Integer id);

    //查询有效的分类数据
    public List<ItemCat> getItemCats();

    public Integer addItemCat(ItemCat itemCat);
}
