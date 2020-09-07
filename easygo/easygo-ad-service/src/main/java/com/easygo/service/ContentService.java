package com.easygo.service;

import com.easygo.pojo.Content;

import java.util.List;
import java.util.Map;

/**
 * @BelongsProject: easygo
 * @BelongsPackage: com.easygo.service
 * @Author: bruceliu
 * @QQ:1241488705
 * @CreateTime: 2020-03-30 14:57
 * @Description: TODO
 */
public interface ContentService {

    public Integer getTotalCount();

    public List<Content> getContents(Map<String,Object> map);

    //查询指定类目下的所有有效广告
    public List<Content> getContentsByCategoryId(Integer categoryId);

    //有效无效功能
    public Integer updateStatus(Map<String,Object> map);

    //新增广告
    public Integer addContent(Content content);

    //根据ID查询对象
    public Content getContentById(Integer id);

    //更新广告
    public Integer updateContent(Content content);
}
