package com.easygo.controller;

import com.easygo.pojo.GoodsDesc;
import com.easygo.service.GoodsDescService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @BelongsProject: easygo
 * @BelongsPackage: com.easygo.controller
 * @Author: bruceliu
 * @QQ:1241488705
 * @CreateTime: 2020-04-14 09:47
 * @Description: TODO
 */
@RestController
@Scope("prototype")
public class GoodsDescController {

    @Autowired
    GoodsDescService goodsDescService;

    @RequestMapping("goodsDesc_getById")
    public GoodsDesc getGoodsDescById(@RequestParam("id") Long id){
        return  goodsDescService.getGoodsDescById(id);
    }

}
