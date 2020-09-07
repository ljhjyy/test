package com.easygo.service.impl;

import com.easygo.mapper.TypeTemplateMapper;
import com.easygo.pojo.TypeTemplate;
import com.easygo.service.TypeTemplateService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @BelongsProject: easygo
 * @BelongsPackage: com.easygo.service.impl
 * @Author: bruceliu
 * @QQ:1241488705
 * @CreateTime: 2020-04-03 09:56
 * @Description: TODO
 */
@Service
public class TypeTemplateServiceImpl implements TypeTemplateService {

    @Resource
    TypeTemplateMapper typeTemplateMapper;

    @Override
    public Integer getTotalCount(Map<String, Object> map) {
        return typeTemplateMapper.getTotalCount(map);
    }

    @Override
    public List<TypeTemplate> getTypeTemplatePage(Map<String, Object> map) {
        return typeTemplateMapper.getTypeTemplatePage(map);
    }

    @Override
    public Integer addTypeTemplate(TypeTemplate typeTemplate) {
        return typeTemplateMapper.addTypeTemplate(typeTemplate);
    }

    @Override
    public TypeTemplate getTypeTemplateById(Integer id) {
        return typeTemplateMapper.getTypeTemplateById(id);
    }

    @Override
    public List<TypeTemplate> getTypeTemplates() {
        return typeTemplateMapper.getTypeTemplates();
    }
}
