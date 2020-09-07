package com.easygo.mapper;

import com.easygo.pojo.TypeTemplate;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @BelongsProject: easygo
 * @BelongsPackage: com.easygo.mapper
 * @Author: bruceliu
 * @QQ:1241488705
 * @CreateTime: 2020-04-03 09:51
 * @Description: TODO
 */
@Mapper
public interface TypeTemplateMapper {

    public Integer getTotalCount(Map<String,Object> map);

    public List<TypeTemplate> getTypeTemplatePage(Map<String,Object> map);

    public Integer addTypeTemplate(TypeTemplate typeTemplate);

    public TypeTemplate getTypeTemplateById(Integer id);

    public List<TypeTemplate> getTypeTemplates();
}
