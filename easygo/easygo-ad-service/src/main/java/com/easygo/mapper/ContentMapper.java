package com.easygo.mapper;

import com.easygo.pojo.Content;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @BelongsProject: easygo
 * @BelongsPackage: com.easygo.mapper
 * @Author: bruceliu
 * @QQ:1241488705
 * @CreateTime: 2020-03-30 14:48
 * @Description: TODO
 */
@Mapper
public interface ContentMapper {

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
