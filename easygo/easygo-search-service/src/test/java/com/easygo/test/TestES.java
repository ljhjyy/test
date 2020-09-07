package com.easygo.test;

import com.easygo.client.GoodsClient;
import com.easygo.pojo.Goods;
import com.easygo.service.GoodsService;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @BelongsProject: easygo
 * @BelongsPackage: com.easygo.test
 * @Author: bruceliu
 * @QQ:1241488705
 * @CreateTime: 2020-04-10 15:20
 * @Description: TODO
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestES {

    //如果要操作Redis，需要获取一个 RedisTemplate
    //如果要操作ES，需要获取 ElasticsearchTemplate,天下我有！
    @Resource
    ElasticsearchTemplate elasticsearchTemplate;

    @Resource
    GoodsClient goodsClient;

    @Autowired
    GoodsService goodsService;

    /**
     * 01-测试工具类
     */
    @Test
    public void testConn(){
        System.out.println("ES操作的工具类:"+elasticsearchTemplate);
    }

    /**
     *02-创建一个索引库? Goods索引库，实际的项目搜索的是商品数据！
     */
    @Test
    public void testCreateIndex(){
        //创建索库,索引库的名字是??
        elasticsearchTemplate.createIndex(Goods.class);
        System.out.println("测试创建索引库成功~");
        elasticsearchTemplate.putMapping(Goods.class);//因为类上面有注解
        System.out.println("创建Goods的Mapping完成");
    }

    /**
     * 新增数据库中的数据到ES索引库中
     */
    @Test
    public void testAddDocument(){
        List<Goods> goodsList = goodsClient.getGoods(1);
        for (Goods goods : goodsList) {
            System.out.println("正在导入："+goods);
        }
        goodsService.saveDocuments(goodsList);
        System.out.println("批量新增索引库数据成功......");
    }

    /**
     * 根据ID查询
     */
    @Test
    public void testgetDocumentById(){
        Goods goods = goodsService.getDocumentById(149187842868047L);
        System.out.println("查询的对象是:"+goods);
    }

    /**
     * 根据ID更新
     */
    @Test
    public void testUpdateById(){
        Goods goods = goodsService.getDocumentById(149187842867986L);
        System.out.println("查询的原对象是:"+goods);
        goods.setGoods_name("阿玛尼装逼神器");
        goods.setCaption("阿玛尼装逼神器，泡妞必备,值得哟拥有");
        goods.setPrice(250.0);
        goodsService.updateDocumentById(goods);
        System.out.println("更新document");
    }

    /**
     * 根据ID删除
     */
    @Test
    public void testDeleteByid(){
        goodsService.deleteDocumentById(149187842867914L);
        System.out.println("删除成功");
    }

    /**
     * 删除所有
     */
    @Test
    public void testDeleteAll(){
        goodsService.deleteAllDocument();
        System.out.println("全部干光了");
    }

    /**
     * 测试关键词查询01  条件查询分页
     */
    @Test
    public void testQuery1(){
        int pageIndex=4; //当前页码
        int pageSize=3; //页码大小
        String keywords="手机"; //用户输入的关键词

        //构建一个查询对象
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                //设置查询条件，可以构建多个条件
                .withQuery(QueryBuilders.queryStringQuery(keywords).defaultField("goods_name"))
                //设置分页的信息,页码从0开始计算
                .withPageable(PageRequest.of(pageIndex - 1, pageSize)).build();

        //条件查询分页，返回分页对象
        AggregatedPage<Goods> page = elasticsearchTemplate.queryForPage(searchQuery, Goods.class);
        System.out.println("当前页码:"+pageIndex);
        System.out.println("页面大小:"+pageSize);
        System.out.println("总页数:"+page.getTotalPages());
        System.out.println("总条数:"+page.getTotalElements());
        System.out.println("每页到的数据是:");
        List<Goods> goodsList = page.getContent();
        for (Goods goods : goodsList) {
            System.out.println(goods);
        }

    }

    /**
     * 多条件搜索 分页
     */
    @Test
    public void testtestQuery2(){
        int pageIndex=1; //当前页码
        int pageSize=3; //页码大小
        String keywords="火爆"; //用户输入的关键词

        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
        if(keywords!=null&&!"".equals(keywords)){
            builder.withQuery(QueryBuilders.multiMatchQuery(keywords, "goods_name", "caption"));
        }
        //设置分页的信息,页码从0开始计算
        builder.withPageable(PageRequest.of(pageIndex - 1, pageSize));
        NativeSearchQuery searchQuery = builder.build();

        //条件查询分页，返回分页对象
        AggregatedPage<Goods> page = elasticsearchTemplate.queryForPage(searchQuery, Goods.class);
        System.out.println("当前页码:"+pageIndex);
        System.out.println("页面大小:"+pageSize);
        System.out.println("总页数:"+page.getTotalPages());
        System.out.println("总条数:"+page.getTotalElements());
        System.out.println("每页到的数据是:");
        List<Goods> goodsList = page.getContent();
        for (Goods goods : goodsList) {
            System.out.println(goods);
        }
    }


    /**
     * 查询高亮
     */
    @Test
    public void testQueryHighLight(){

        int pageIndex=1; //当前页码
        int pageSize=3; //页码大小
        String keywords="手机"; //用户输入的关键词

        //设置高亮规则
        HighlightBuilder.Field nameField=new HighlightBuilder.Field("*") //所有出现的关键字
                .preTags("<span style='color:red'>") //关键字的前头
                .postTags("</span>") //关键的后头
                .requireFieldMatch(false);

        //构造搜索条件
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
        if(keywords!=null&&!"".equals(keywords)){
            builder.withQuery(QueryBuilders.multiMatchQuery(keywords, "goods_name", "caption"));
        }
        //设置分页的信息,页码从0开始计算
        builder.withPageable(PageRequest.of(pageIndex - 1, pageSize));
        //设置搜搜的时候高亮
        builder.withHighlightFields(nameField);
        NativeSearchQuery searchQuery = builder.build();

        //在查询的时候使用 SearchResultMapper接口，对查询的结果进行修饰


        //条件查询分页，返回分页对象
        AggregatedPage<Goods> page = elasticsearchTemplate.queryForPage(searchQuery, Goods.class,new SearchResultMapper(){

            //对查询的结果集中的关键词进行包装
            @Override
            public <T> AggregatedPage<T> mapResults(SearchResponse response, Class<T> clazz, Pageable pageable) {
                //获取搜索到的内容
                SearchHits searchHits = response.getHits();
                //数组
                SearchHit[] hits = searchHits.getHits();
                ArrayList<Goods> list = new ArrayList<Goods>();
                //循环数组
                for (SearchHit hit : hits) {
                    Goods g = new Goods();
                    //原始map
                    Map<String, Object> sourceAsMap = hit.getSourceAsMap();
                    g.setId(Long.parseLong(sourceAsMap.get("id").toString())); //ID普通字段
                    g.setGoods_name(sourceAsMap.get("goods_name").toString()); // 普通显示
                    g.setCaption(sourceAsMap.get("caption").toString()); // 普通副标题
                    g.setPrice(Double.parseDouble(sourceAsMap.get("price").toString()));//商品价格
                    g.setSmall_pic(sourceAsMap.get("small_pic").toString()); //商品图片
                    //高亮
                    Map<String, HighlightField> highlightFields = hit.getHighlightFields();
                    if (highlightFields.get("goods_name") != null) {
                        String nameHighlight = highlightFields.get("goods_name").getFragments()[0].toString();
                        g.setGoods_name(nameHighlight);
                    }
                    if (highlightFields.get("caption") != null) {
                        String contentHighlight = highlightFields.get("caption").getFragments()[0].toString();
                        g.setCaption(contentHighlight);
                    }
                    list.add(g);
                }
                return new AggregatedPageImpl<T>((List<T>) list);
            }
        });

        // page_withoutHighlight不高亮
        AggregatedPage<Goods> page_withoutHighlight = elasticsearchTemplate.queryForPage(searchQuery, Goods.class);//没有高亮
        System.out.println("当前页码:"+pageIndex);
        System.out.println("页面大小:"+pageSize);
        System.out.println("总页数:"+page_withoutHighlight.getTotalPages());
        System.out.println("总条数:"+page_withoutHighlight.getTotalElements());
        System.out.println("每页到的数据是:");

        //page 高亮之后的page
        List<Goods> goodsList = page.getContent();
        for (Goods goods : goodsList) {
            System.out.println(goods);
        }
    }

}
