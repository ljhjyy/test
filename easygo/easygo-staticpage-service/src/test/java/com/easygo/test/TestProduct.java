package com.easygo.test;

import com.easygo.client.*;
import com.easygo.pojo.Brand;
import com.easygo.pojo.Goods;
import com.easygo.pojo.GoodsDesc;
import com.easygo.pojo.Item;
import com.easygo.utils.AttributeNameObject;
import com.easygo.utils.JsonUtils;
import com.easygo.utils.OrderUrl;
import com.easygo.utils.SkuBean;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @BelongsProject: easygo
 * @BelongsPackage: com.easygo.test
 * @Author: bruceliu
 * @QQ:1241488705
 * @CreateTime: 2020-04-13 16:26
 * @Description: 生成静态的商品详情页面
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestProduct {

    @Autowired
    GoodsClient goodsClient;

    @Autowired
    GoodsDescClient goodsDescClient;

    @Autowired
    BrandClinet brandClinet;

    @Autowired
    ItemCatClient itemCatClient;

    @Autowired
    ItemClient itemClient;

    /**
     * 商品详情页面静态生成
     */
    @Test
    public void createProductHtml() throws Exception{
        //商品对象
        Goods goods = goodsClient.getGoodsById(149187842868045L);
        //Goods goods = goodsClient.getGoodsById(149187842868052L);
        System.out.println("商品对象:"+goods);
        GoodsDesc goodsDesc = goodsDescClient.getGoodsDescById(goods.getId());
        System.out.println("商品详情对象:"+goodsDesc);

        //商品图片集合
        //[{"order":"1","url":"https://img12.360buyimg.com/n7/jfs/t1/85744/15/1824/248878/5dc4d244E86ffb8c2/a448e66a8609b7b4.jpg"},
        // {"order":"2","url":"https://img10.360buyimg.com/n7/jfs/t1/64572/27/14834/220904/5dc4c97fE96f5a3e7/969347feb6a2a4b0.jpg"}]
        String item_images = goodsDesc.getItem_images();
        //商品图片的集合数据
        List<OrderUrl> orderUrls = JsonUtils.parseitem_images2List(item_images);
        //品牌ID
        Integer brand_id = goods.getBrand_id();
        //品牌对象
        Brand brand = brandClinet.getBrandById(brand_id);
        System.out.println("品牌对象:"+brand);

        //分类数据
        Integer category1_id = goods.getCategory1_id();//一级类目ID
        String name1 = itemCatClient.getItemCatById(category1_id).getName();//一级类目名称
        Integer category2_id = goods.getCategory2_id();//二级类目ID
        String name2 = itemCatClient.getItemCatById(category2_id).getName();//二级类目名称
        Integer category3_id = goods.getCategory3_id();//三级类目ID
        String name3 = itemCatClient.getItemCatById(category3_id).getName();//三级类目名称

        //加载商品的规格数据
        // [{"attributeName":"鲜肉产地","attributeValue":["北京","内蒙古","上海"]}]
        String specification_items = goodsDesc.getSpecification_items();
        //规格的集合
        List<AttributeNameObject> attributeNameObjects = JsonUtils.parseAttributeName2List(specification_items);
        System.out.println("规格的集合:"+attributeNameObjects);

        //Sku的集合,一个商品下有多个SKU
        List<Item> skus = itemClient.getItemsByGoodsId(goods.getId());
        List<SkuBean> skulist=new ArrayList<>();
        for (Item item : skus) {
            Long sku_id = item.getId(); // 1369300
            String sku_title = item.getTitle(); // 天梭(TISSOT)瑞士手表 力洛克系列机械男表 50mm 牛皮
            Double sku_price = item.getPrice(); // 5200.00
            String sku_spec = item.getSpec(); // {"表盘尺寸":"60mm","表带材质":"不锈钢"}
            Map map = JsonUtils.json2Map(sku_spec);
            SkuBean skuBean=new SkuBean(sku_id,sku_title,sku_price,map);
            skulist.add(skuBean);
        }
        //把skulist转为JSON格式
        String sku_jsons = new ObjectMapper().writeValueAsString(skulist);
        System.out.println("sku_jsons:"+sku_jsons);

        //把商品对象存值，生成详情页面
        //1.创建一个模板加载器
        ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
        resolver.setPrefix("templates/"); //模板所在的路径
        resolver.setSuffix(".html"); //模板的后缀
        //2.创建模板引擎
        TemplateEngine templateEngine = new TemplateEngine();
        //3.加载器加入模板引擎
        templateEngine.setTemplateResolver(resolver);
        //4.设置静态页面生成的文件地址
        FileWriter fileWriter=new FileWriter("D:/XXXOOO/"+goods.getId()+".html");
        //5.创建Context对象
        Context context= new Context();

        //6.放入测试数据,填充数据,填充的是对象集合 goods
        context.setVariable("goods",goods); //商品对象
        context.setVariable("goodsDesc",goodsDesc); //商品详情对象
        context.setVariable("orderUrls",orderUrls); //商品图片集合
        context.setVariable("brand",brand); //商品品牌对象
        context.setVariable("name1",name1); //一级类目名称
        context.setVariable("name2",name2); //二级类目名称
        context.setVariable("name3",name3); //三级类目名称
        context.setVariable("attributeNameObjects",attributeNameObjects); //商品的规格集合
        context.setVariable("sku_jsons",sku_jsons); //商品的SKU(JSON格式)

        //7.生成页面,指定模板
        templateEngine.process("product",context,fileWriter);
        System.out.println(goods.getId()+".html商品详情页面生成完毕~~~~");

        fileWriter.close();
    }

}
