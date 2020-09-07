package com.easygo.controller;

import com.easygo.api.ItemCatClient;
import com.easygo.pojo.ItemCat;
import com.easygo.utils.MessageResults;
import com.easygo.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.font.TextRecord;
import sun.plugin2.message.Message;

import java.util.HashMap;
import java.util.Map;

/**
 * @BelongsProject: easygo
 * @BelongsPackage: com.easygo.controller
 * @Author: bruceliu
 * @QQ:1241488705
 * @CreateTime: 2020-04-07 10:16
 * @Description: TODO
 */
@Controller
@Scope("prototype")
public class ItemCatController {

    @Autowired
    ItemCatClient itemCatClient;

    //跳转更新页面
    @RequestMapping("/itemcat_goupdate")
    @ResponseBody
    public ItemCat itemcat_goupdate(Integer id){
        //回显的对象
        return itemCatClient.getItemCatById(id);
    }

    @RequestMapping("/updateItemCat")
    @ResponseBody
    public MessageResults updateItemCat(ItemCat itemCat){
        System.out.println("更新itemCat:"+itemCat);
        MessageResults results = null;
        //int count = itemCatClient.updateItemCat(itemCat);
        int count =1;
        if (count > 0) {
            results = new MessageResults(200, "更新成功");
        } else {
            results = new MessageResults(500, "更新失败");
        }
        return results;
    }

    @RequestMapping("/addItemCat")
    @ResponseBody
    public MessageResults itemcat_add(ItemCat itemCat){
        System.out.println("新增itemCat:"+itemCat);
        MessageResults results = null;
        int count = itemCatClient.addItemCat(itemCat);
        if (count > 0) {
            results = new MessageResults(200, "新增成功");
        } else {
            results = new MessageResults(500, "新增失败");
        }
        return results;
    }

    /**
     * 01-商品分类的接口
     *
     * @param pageIndex
     * @param pageSize
     * @param parent_id
     * @return
     */
    @RequestMapping("/itemcat_page")
    public String itemcat_page(@RequestParam(value = "pageIndex", defaultValue = "1") Integer pageIndex,
                               @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                               @RequestParam(value = "parent_id", defaultValue = "0") Integer parent_id, Model model) {
        PageUtils<ItemCat> pageUtils = itemCatClient.itemcat_page(pageIndex, pageSize, parent_id);


        boolean flag=false;//是否是三级类目
        //动态生成页面的导航条
        String str = "<li><a href=\"javascript:queryChilds(0);\" >顶级分类列表</a></li>";//1级分类
        if (parent_id != 0) {
            //表示子分类
            ItemCat item1 = itemCatClient.getItemCatById(parent_id);//1级分类
            if (item1 != null) {
                //表示有2级分类
                String name1 = item1.getName(); //2级分类
                String str1 = "<li><a href=\"javascript:queryChilds("+item1.getId()+");\" >" + name1 + "</a></li>";
                Integer parent_id1 = item1.getParent_id();
                ItemCat item2 = itemCatClient.getItemCatById(parent_id1);
                if (item2 != null) {
                    String name2 = item2.getName();//3级分类
                    str = str + "<li><a href=\"javascript:queryChilds("+item2.getId()+");\" >" + name2 + "</a></li>";
                    flag= true; //表示已经是第三级类目
                }
                str = str + str1;
            }
        }
        System.out.println(str);

        model.addAttribute("str", str);//导航菜单条
        model.addAttribute("flag", flag);//是否是第三级类目
        model.addAttribute("pageUtils", pageUtils);
        //回显ParentID
        model.addAttribute("parent_id", parent_id);
        return "item_cat";
    }
}
