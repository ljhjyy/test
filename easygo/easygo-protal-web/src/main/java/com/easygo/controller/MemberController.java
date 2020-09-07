package com.easygo.controller;

import com.easygo.config.CasConfig;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @BelongsProject: easygo
 * @BelongsPackage: com.easygo.controller
 * @Author: bruceliu
 * @QQ:1241488705
 * @CreateTime: 2020-04-20 11:21
 * @Description: 用户中心控制器
 */
@Controller
@Scope("prototype")
public class MemberController {

    @Autowired
    CasConfig casConfig;

    /**
     * 01-用户中心
     * @return
     */
    @RequestMapping("/member_index")
    public String memberIndex(){
        System.out.println("用户中心.....");
        return "member_index";
    }


    /**
     * 02-用户注销登出
     * @param attributes
     * @return
     */
    @RequestMapping(value = "/member_logout", method = RequestMethod.GET)
    public String logout(RedirectAttributes attributes) {
        SecurityUtils.getSubject().logout();
        attributes.addFlashAttribute("message", "您已安全退出");
        return "redirect:" + casConfig.getCasServerLogoutUrl();
    }

    /**
     * 03-跳转
     * @return
     */
    @RequestMapping("/member_login")
    public String member_login(){
        //不走模板引擎了，走跳转地址
        return "redirect:member_index";
    }


}
