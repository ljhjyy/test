package com.easygo.controller;

import com.easygo.pojo.Specification;
import com.easygo.pojo.SpecificationOption;
import com.easygo.service.SpecificationOptionService;
import com.easygo.service.SpecificationService;
import com.easygo.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @BelongsProject: easygo
 * @BelongsPackage: com.easygo.controller
 * @Author: bruceliu
 * @QQ:1241488705
 * @CreateTime: 2020-04-01 11:47
 * @Description: TODO
 */
@RestController
@Scope("prototype")
public class SpecificationController {

    @Autowired
    SpecificationService specificationService;

    @Autowired
    SpecificationOptionService specificationOptionService;

    /**
     * 查询所有有效的规格
     * @return
     */
    @RequestMapping("/specifications_all")
    public List<Specification> getSpecifications(){
        return specificationService.getSpecifications();
    }

    @RequestMapping("/specification_update")
    public Integer specification_update(@RequestParam("id") Integer id, @RequestParam("spec_name") String spec_name, @RequestParam("option_name") String[] option_name, @RequestParam("orders") Integer[] orders) {
        //两个表中的值
        System.out.println(id);
        System.out.println(spec_name);
        System.out.println(Arrays.toString(option_name));
        System.out.println(Arrays.toString(orders));
        return  specificationService.specification_update(id, spec_name, option_name, orders);
    }

    /**
     * 准备要回显的数据
     * @param id
     * @return
     */
    @RequestMapping("/getspecification_getById")
    public Specification getSpecificationById(@RequestParam Integer id) {
        //规格对象
        Specification specification = specificationService.getSpecificationById(id);
        return specification;
    }

    /**
     * 准备要回显的数据
     *
     * @param id
     * @return
     */
    @RequestMapping("/specification_getById")
    public Map<String, Object> getSpecification(@RequestParam Integer id) {
        //规格对象
        Specification specification = specificationService.getSpecificationById(id);
        //规格对象下的选项
        List<SpecificationOption> specificationOptions = specificationOptionService.getSpecificationOptionsBySpecificationId(specification.getId());
        Map<String, Object> map = new HashMap<>();
        map.put("specification", specification);
        map.put("specificationOptions", specificationOptions);
        return map;
    }

    /**
     * 服务器端新增接口
     *
     * @param spec_name
     * @param option_name
     * @param orders
     * @return
     */
    @RequestMapping("/specification_add")
    public Integer specification_add(@RequestParam("spec_name") String spec_name, @RequestParam("option_name") String[] option_name, @RequestParam("orders") Integer[] orders) {
        System.out.println("服务端:");
        System.out.println(spec_name);
        System.out.println(Arrays.toString(option_name));
        System.out.println(Arrays.toString(orders));
        //事务开启是在业务逻辑层
        Integer count = specificationService.addSpecificationTransaction(spec_name, option_name, orders);
        return count;
    }

    /**
     * 规格服务的后台
     *
     * @param pageIndex
     * @param pageSize
     * @param spec_name
     * @return
     */
    @RequestMapping("/specification_pages")
    public PageUtils<Specification> specification_pages(
            @RequestParam(defaultValue = "1", required = false) Integer pageIndex,
            @RequestParam(defaultValue = "5", required = false) Integer pageSize, @RequestParam(defaultValue = "", required = false) String spec_name) {
        Map<String, Object> map = new HashMap<>(0);
        map.put("pageStart", (pageIndex - 1) * pageSize);
        map.put("pageSize", pageSize);
        map.put("spec_name", "%" + spec_name + "%");
        System.out.println("条件:" + map);
        Integer totalCount = specificationService.totalCount(map);
        List<Specification> specifications = specificationService.getSpecificationPage(map);
        PageUtils<Specification> pageUtils = new PageUtils<>(pageIndex, pageSize, totalCount, specifications);
        System.out.println("数据:" + pageUtils);
        return pageUtils;
    }

}
