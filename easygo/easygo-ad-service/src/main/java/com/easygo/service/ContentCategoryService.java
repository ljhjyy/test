package com.easygo.service;

import com.easygo.pojo.ContentCategory;

import java.util.List;
import java.util.Map;

/**
 * @BelongsProject: easygo
 * @BelongsPackage: com.easygo.service
 * @Author: bruceliu
 * @QQ:1241488705
 * @CreateTime: 2020-03-30 10:23
 * @Description: TODO
 */
public interface ContentCategoryService {

    public Integer getTotalCount(Map<String, Object> map);

    public List<ContentCategory> getContentCategoryPages(Map<String, Object> map);

    public int addContentCategory(ContentCategory category);

    public int updateContentCategory(ContentCategory category);

    public int deleteContentCategory(Integer id);

    public ContentCategory getById(Integer id);

    //查询所有的广告分类--下拉框使用
    public List<ContentCategory> getContentCategorys();

}
