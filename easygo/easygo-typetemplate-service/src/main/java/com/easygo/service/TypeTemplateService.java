package com.easygo.service;

import com.easygo.pojo.TypeTemplate;

import java.util.List;
import java.util.Map;

/**
 * @BelongsProject: easygo
 * @BelongsPackage: com.easygo.service
 * @Author: bruceliu
 * @QQ:1241488705
 * @CreateTime: 2020-04-03 09:56
 * @Description: TODO
 */
public interface TypeTemplateService {

    public Integer getTotalCount(Map<String,Object> map);

    public List<TypeTemplate> getTypeTemplatePage(Map<String,Object> map);

    public Integer addTypeTemplate(TypeTemplate typeTemplate);

    public TypeTemplate getTypeTemplateById(Integer id);

    public List<TypeTemplate> getTypeTemplates();
}
