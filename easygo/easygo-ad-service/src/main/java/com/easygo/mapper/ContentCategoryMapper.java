package com.easygo.mapper;

import com.easygo.pojo.ContentCategory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @BelongsProject: easygo
 * @BelongsPackage: com.easygo.mapper
 * @Author: bruceliu
 * @QQ:1241488705
 * @CreateTime: 2020-03-30 09:59
 * @Description: TODO
 */
@Mapper
public interface ContentCategoryMapper {

    public Integer getTotalCount(Map<String, Object> map);

    public List<ContentCategory> getContentCategoryPages(Map<String, Object> map);

    public int addContentCategory(ContentCategory category);

    public int updateContentCategory(ContentCategory category);

    public int deleteContentCategory(Integer id);

    public ContentCategory getById(Integer id);

    //查询所有的广告分类--下拉框使用
    public List<ContentCategory> getContentCategorys();

}
