package com.easygo.controller;

import com.easygo.api.BrandClient;
import com.easygo.api.SpecificationClient;
import com.easygo.api.TypeTemplateClient;
import com.easygo.pojo.Brand;
import com.easygo.pojo.Specification;
import com.easygo.pojo.TypeTemplate;
import com.easygo.utils.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.plugin2.message.Message;

import java.util.*;

/**
 * @BelongsProject: easygo
 * @BelongsPackage: com.easygo.controller
 * @Author: bruceliu
 * @QQ:1241488705
 * @CreateTime: 2020-04-03 10:05
 * @Description: TODO
 */
@Controller
@Scope("prototype")
public class TypeTemplateController {

    @Autowired
    TypeTemplateClient typeTemplateClient;

    @Autowired
    BrandClient brandClient;

    @Autowired
    SpecificationClient specificationClient;

    /**
     * 01-所有的模板
     * @return
     */
    @RequestMapping("/typeTemplate_all")
    @ResponseBody
    public List<TypeTemplate> getTypeTemplates(){
        return typeTemplateClient.getTypeTemplates();
    }

    /**
     * 跳转更新数据回显
     * @param id
     * @return
     */
    @RequestMapping("/typeTemplate_goUpdate")
    @ResponseBody
    public Map<String,Object> goUpdate(Integer id) throws Exception{
        Map<String,Object> map=new HashMap<>();
        //数据库中对象-----JSON数据
        System.out.println("ID值是:"+id);
        TypeTemplate typeTemplate = typeTemplateClient.getTypeTemplateById(id);

        //选中的那些品牌
        List<JsonObject> brands_objects = JsonUtils.parsejson2List(typeTemplate.getBrand_ids());
        //所有的有效品牌
        List<Brand> brands = brandClient.getBrands();
        for(Brand b:brands){
            for(JsonObject o:brands_objects){
                if(b.getId().equals(o.getId())){
                    //选中，前端页面知道？
                    b.setFlag(true);
                }
            }
        }

        //选中的那些品牌
        List<JsonObject> spec_objects = JsonUtils.parsejson2List(typeTemplate.getSpec_ids());
        //所有的有效规格
        List<Specification> specifications = specificationClient.getSpecifications();
        for (Specification specification : specifications) {
            for(JsonObject o:spec_objects){
                if(specification.getId().equals(o.getId())){
                    specification.setFlag(true);
                }
            }
        }

        //扩展属性  [{"text":"内存大小"},{"text":"颜色"}]
        String custom_attribute_items = typeTemplate.getCustom_attribute_items();
        ObjectMapper objectMapper=new ObjectMapper();
        List<Attribute_items_bean> attribute_items_beans= objectMapper.readValue(custom_attribute_items,new TypeReference<ArrayList<Attribute_items_bean>>(){});

        map.put("typeTemplate",typeTemplate);
        map.put("attribute_items_beans",attribute_items_beans);
        map.put("brands",brands);
        map.put("specifications",specifications);
        return map;
    }



    /**
     * 新增模板
     * @param typeTemplate
     * @return
     */
    @RequestMapping("/typeTemplate_add")
    @ResponseBody
    public MessageResults addTypeTemplate(TypeTemplate typeTemplate,Integer[] brandIds,Integer[] specIds,String[] attribute_items) throws Exception{
        MessageResults results = null;
        System.out.println("选中的品牌ID："+ Arrays.toString(brandIds));
        System.out.println("选中的规格ID："+Arrays.toString(specIds));
        System.out.println("扩展属性集合："+Arrays.toString(attribute_items));

        List<JsonObject> jsonObjects_brands=new ArrayList<>();
        for (Integer brandId : brandIds) {
            String text = brandClient.getBrandById(brandId).getName();
            JsonObject jsonObject=new JsonObject(brandId,text);
            jsonObjects_brands.add(jsonObject);
        }
        ObjectMapper objectMapper=new ObjectMapper();

        String json_brands=objectMapper.writeValueAsString(jsonObjects_brands);
        System.out.println("选中的品牌JSON:"+json_brands);
        typeTemplate.setBrand_ids(json_brands);

        List<JsonObject> jsonObjects_specs=new ArrayList<>();
        for (Integer specId : specIds) {
            String text = specificationClient.getSpecificationById(specId).getSpec_name();
            JsonObject jsonObject=new JsonObject(specId,text);
            jsonObjects_specs.add(jsonObject);
        }
        String json_specs=objectMapper.writeValueAsString(jsonObjects_specs);
        System.out.println("选中的规格JSON:"+json_specs);
        typeTemplate.setSpec_ids(json_specs);

        List<Attribute_items_bean> attribute_items_beanList=new ArrayList<Attribute_items_bean>();
        for(String text:attribute_items){
            Attribute_items_bean bean=new Attribute_items_bean(text);
            attribute_items_beanList.add(bean);
        }
        String json_attribute_items=objectMapper.writeValueAsString(attribute_items_beanList);
        System.out.println("扩展属性JSON:"+json_attribute_items);
        typeTemplate.setCustom_attribute_items(json_attribute_items);

        System.out.println("要新增的对象是："+typeTemplate);

        int count =typeTemplateClient.addTypeTemplate(typeTemplate);
        if (count > 0) {
            results = new MessageResults(200, "新增成功");
        } else {
            results = new MessageResults(500, "新增失败");
        }
        return results;
    }

    /**
     *  新增界面的页面回显
     */
    @RequestMapping("/typeTemplate_getBrandsAndspecifications")
    @ResponseBody
    public Map<String,Object> typeTemplate_getBrandsAndspecifications(){
        //所有的有效品牌
        List<Brand> brands = brandClient.getBrands();
        //所有的有效规格
        List<Specification> specifications = specificationClient.getSpecifications();
        Map<String,Object> map=new HashMap<>();
        map.put("brands",brands);
        map.put("specifications",specifications);
        return map;
    }


    /**
     * 01-模板分页
     * @param pageIndex
     * @param pageSize
     * @param name
     * @return
     */
    @RequestMapping("typeTemplate_page")
    public String getTypeTemplatePage(@RequestParam(defaultValue = "1", required = false) Integer pageIndex,
                                      @RequestParam(defaultValue = "5", required = false) Integer pageSize, @RequestParam(defaultValue = "", required = false) String name,
                                      Model model) {
        PageUtils<TypeTemplate> pageUtils = typeTemplateClient.getTypeTemplatePage(pageIndex, pageSize, name);
        model.addAttribute("pageUtils",pageUtils);
        model.addAttribute("name",name);
        return "type_template";
    }

}
