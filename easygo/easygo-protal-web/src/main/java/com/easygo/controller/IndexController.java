package com.easygo.controller;

import com.easygo.api.ContentClient;
import com.easygo.api.ItemCatClient;
import com.easygo.pojo.Admin;
import com.easygo.pojo.Content;
import com.easygo.pojo.ItemCat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * @BelongsProject: easygo
 * @BelongsPackage: com.easygo.controller
 * @Author: bruceliu
 * @QQ:1241488705
 * @CreateTime: 2020-03-30 16:02
 * @Description: 首页控制器
 */
@Controller
@Scope("prototype")
public class IndexController {

    @Autowired
    ContentClient contentClient;

    @Autowired
    ItemCatClient itemCatClient;

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    /**
     * 01-首页加载
     *
     * @param model
     * @return
     */
    @RequestMapping({"/", "/index"})
    public String index(Model model) {
        //加载分类
        getItemCats(model);
        //查下轮播图广告(轮播图)
        getContents(model);
        //跳转首页
        return "index";
    }

    //加载商品类目
    public void getItemCats(Model model) {
        //所有的类目
        List<ItemCat> itemCats = itemCatClient.getItemCats();
        //所有的1级分类
        List<ItemCat> itemCats_one = extractItemCats("1", itemCats);
        //所有的2级分类
        List<ItemCat> itemCats_two = extractItemCats("2", itemCats, itemCats_one);
        //所有的3级分类
        List<ItemCat> itemCats_third = extractItemCats("3", itemCats, itemCats_two);
        //存值
        model.addAttribute("itemCats_one", itemCats_one);
        model.addAttribute("itemCats_two", itemCats_two);
        model.addAttribute("itemCats_third", itemCats_third);

    }

    //提取类目
    public List<ItemCat> extractItemCats(String type, List<ItemCat>... sourceList) {
        List<ItemCat> emptyList = new ArrayList<>();
        //所有的分类
        List<ItemCat> allList = sourceList[0];
        //所有的1级分类
        if ("1".equals(type)) {
            for (ItemCat itemCat : allList) {
                if (itemCat.getParent_id().toString().equals("0")) {
                    emptyList.add(itemCat);
                    if (emptyList.size() == 10) {
                        //由于首页数据无法显示全，那么就只显示10个，意思一下！
                        break;
                    }
                }
            }
        }
        //所有的2级分类
        if ("2".equals(type)) {
            //所有的1级分类
            List<ItemCat> itemCats_one = sourceList[1];
            for (ItemCat itemCat : itemCats_one) {
                for (ItemCat item : allList) {
                    if (item.getParent_id().toString().equals(itemCat.getId().toString())) {
                        emptyList.add(item);
                    }
                }
            }
        }
        //所有的3级分类
        if ("3".equals(type)) {
            //所有的2级分类
            List<ItemCat> itemCats_two = sourceList[1];
            for (ItemCat itemCat : itemCats_two) {
                for (ItemCat item : allList) {
                    if (item.getParent_id().toString().equals(itemCat.getId().toString())) {
                        emptyList.add(item);
                    }
                }
            }
        }
        return emptyList;
    }


    /**
     * 加载首页上需要的广告数据
     */
    public void getContents(Model model) {
        //先从缓存中查询一边，是否有缓存数据
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(ArrayList.class));
        List<Content> lunbo_contents = (ArrayList<Content>) redisTemplate.opsForValue().get("lunbo_contents_key");
        List<Content> shengxian_contents = (ArrayList<Content>) redisTemplate.opsForValue().get("shengxian_contents_key");
        if (lunbo_contents == null) {
            System.out.println("缓存中没有轮播图数据....查询数据库");
            //轮播图广告
            //把广告列表ID写到配置文件中，直接发送SQL查询数据
            lunbo_contents = contentClient.getContentsByCategoryId(1);
            //再把查询到数据，存入一份到缓存中
            redisTemplate.opsForValue().set("lunbo_contents_key", lunbo_contents);
        } else {
            System.out.println("缓存中有轮播图数据,不用查询数据库啦....");
        }

        if (shengxian_contents == null) {
            System.out.println("缓存中没有生鲜数据....查询数据库");
            //生鲜楼层广告，直接发送SQL查询数据
            shengxian_contents = contentClient.getContentsByCategoryId(10);
            //再把查询到数据，存入一份到缓存中
            redisTemplate.opsForValue().set("shengxian_contents_key", shengxian_contents);
        }else{
            System.out.println("缓存中有生鲜图数据,不用管查询数据库啦....");
        }
        model.addAttribute("lunbo_contents", lunbo_contents);
        model.addAttribute("shengxian_contents", shengxian_contents);
    }

}
