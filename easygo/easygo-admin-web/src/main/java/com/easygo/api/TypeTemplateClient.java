package com.easygo.api;

import com.easygo.pojo.TypeTemplate;
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
 * @CreateTime: 2020-04-03 10:04
 * @Description: TODO
 */
@FeignClient(value = "easygo-typetemplate-service")
public interface TypeTemplateClient {

    /**
     * 01-所有的模板
     * @return
     */
    @RequestMapping("/typeTemplate_all")
    public List<TypeTemplate> getTypeTemplates();

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    @RequestMapping("/typeTemplate_getById")
    public TypeTemplate getTypeTemplateById(@RequestParam Integer id);

    /**
     * 新增模板
     * @param typeTemplate
     * @return
     */
    @RequestMapping("/typeTemplate_add")
    public Integer addTypeTemplate(@RequestBody TypeTemplate typeTemplate);

    @RequestMapping("typeTemplate_page")
    public PageUtils<TypeTemplate> getTypeTemplatePage(@RequestParam(defaultValue = "1", required = false) Integer pageIndex,
                                                       @RequestParam(defaultValue = "5", required = false) Integer pageSize, @RequestParam(defaultValue = "", required = false) String name);

}
