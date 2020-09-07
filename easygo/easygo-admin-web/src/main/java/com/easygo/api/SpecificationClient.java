package com.easygo.api;

import com.easygo.pojo.Specification;
import com.easygo.utils.PageUtils;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * @BelongsProject: easygo
 * @BelongsPackage: com.easygo.api
 * @Author: bruceliu
 * @QQ:1241488705
 * @CreateTime: 2020-04-01 14:43
 * @Description: TODO
 */
@FeignClient(value = "easygo-specification-service")
public interface SpecificationClient {

    /**
     * 准备要回显的数据
     * @param id
     * @return
     */
    @RequestMapping("/getspecification_getById")
    public Specification getSpecificationById(@RequestParam Integer id);

    /**
     * 查询所有有效的规格
     * @return
     */
    @RequestMapping("/specifications_all")
    public List<Specification> getSpecifications();



    @RequestMapping("/specification_update")
    public Integer specification_update(@RequestParam("id") Integer id, @RequestParam("spec_name") String spec_name, @RequestParam("option_name") String[] option_name, @RequestParam("orders") Integer[] orders) ;

    /**
     * 更新的时候数据回显
     * @param id
     * @return
     */
    @RequestMapping("/specification_getById")
    public Map<String,Object> getSpecification(@RequestParam Integer id);


    @RequestMapping("/specification_add")
    public Integer specification_add(@RequestParam("spec_name") String spec_name,@RequestParam("option_name")  String[] option_name,@RequestParam("orders")  Integer[] orders);

    /**
     * 规格服务的后台
     * @param pageIndex
     * @param pageSize
     * @param spec_name
     * @return
     */
    @RequestMapping("/specification_pages")
    public PageUtils<Specification> specification_pages(
            @RequestParam(defaultValue = "1",required = false) Integer pageIndex,
            @RequestParam(defaultValue = "5",required = false) Integer pageSize,@RequestParam(defaultValue = "", required = false) String spec_name);

}
