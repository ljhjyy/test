package com.easygo.controller;

import com.easygo.api.BrandClient;
import com.easygo.pojo.Brand;
import com.easygo.utils.MessageResults;
import com.easygo.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.jws.WebParam;
import java.util.Arrays;
import java.util.List;

/**
 * @BelongsProject: easygo
 * @BelongsPackage: com.easygo.controller
 * @Author: bruceliu
 * @QQ:1241488705
 * @CreateTime: 2020-03-27 11:24
 * @Description: TODO
 */
@Controller
@Scope("prototype")
public class BrandController {

    @Autowired
    BrandClient brandClient;


    @RequestMapping("/brand_page")
    public String getBrandsByPage(@RequestParam(defaultValue = "1",required = false) Integer pageIndex, @RequestParam(defaultValue = "5",required = false) Integer pageSize, Model model){
        PageUtils<Brand> pageUtils= brandClient.getBrandsByPage(pageIndex,pageSize);
        model.addAttribute("pageUtils",pageUtils);
        return "brand";
    }


    /*
     * 全部查询废弃
     */
    @RequestMapping("/brand_getBrands")
    public String getBrands(Model model) {
        List<Brand> brands = brandClient.getBrands();
        model.addAttribute("brands", brands);
        return "brand";
    }

    @RequestMapping("/brand_add")
    @ResponseBody
    public MessageResults addBrand(Brand brand) {
        MessageResults results = null;
        int count = brandClient.addBrand(brand);
        if (count > 0) {
            results = new MessageResults(200, "新增成功");
        } else {
            results = new MessageResults(500, "新增失败");
        }
        return results;
    }

    @RequestMapping("/brand_getBrandById")
    @ResponseBody
    public Brand getBrandById(Integer id){
        return brandClient.getBrandById(id);
    }

    @RequestMapping("/brand_update")
    @ResponseBody
    public MessageResults brand_update(Brand brand){
        MessageResults results = null;
        System.out.println("要更新的对象是："+brand);
        int count = brandClient.updateBrand(brand);
        if (count > 0) {
            results = new MessageResults(200, "更新成功");
        } else {
            results = new MessageResults(500, "更新失败");
        }
        return results;
    }

    @RequestMapping("/brand_delete")
    @ResponseBody
    public MessageResults delete(Integer id){
        MessageResults results = null;
        int count = brandClient.deleteBrand(id);
        if (count > 0) {
            results = new MessageResults(200, "删除成功");
        } else {
            results = new MessageResults(500, "删除失败");
        }
        return results;
    }

    @RequestMapping("/brand_deleteSome")
    @ResponseBody
    public MessageResults deleteSome(String ids){
        MessageResults results = null;
        //ids=11,12,13
        System.out.println("ids="+ ids);
        int count = brandClient.deleteSome(ids);
        if (count > 0) {
            results = new MessageResults(200, "批量删除成功");
        } else {
            results = new MessageResults(500, "批量删除失败");
        }
        return results;
    }


}
