package com.easygo.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @BelongsProject: easygo
 * @BelongsPackage: com.easygo.controller
 * @Author: bruceliu
 * @QQ:1241488705
 * @CreateTime: 2020-04-16 15:14
 * @Description: TODO
 */
@Controller
@Scope("prototype")
public class CommonController {

    /**
     * 通用的页面跳转方法
     * @param page
     * @return
     */
    @RequestMapping("/page_{page}")
    public String page(@PathVariable("page") String page) {
        return page;
    }
}
