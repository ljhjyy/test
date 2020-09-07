package com.easygo.controller;

import com.easygo.pojo.Goods;
import com.easygo.utils.PageUtils;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.highlight.HighlightBuilder;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @BelongsProject: easygo
 * @BelongsPackage: com.easygo.controller
 * @Author: bruceliu
 * @QQ:1241488705
 * @CreateTime: 2020-04-11 11:04
 * @Description: TODO
 */
@RestController
@Scope("prototype")
public class GoodsSearchController {


    @Resource
    ElasticsearchTemplate elasticsearchTemplate;

    /**
     * 01-搜索的接口
     * @param pageIndex
     * @param pageSize
     * @param keywords
     * @return
     */
    @RequestMapping("/goods_search")
    public PageUtils<Goods> goods_search(
            @RequestParam(value = "pageIndex",defaultValue = "1") Integer pageIndex,
            @RequestParam(value = "pageSize",defaultValue = "20") Integer pageSize,
            @RequestParam(value = "keywords",defaultValue = "") String keywords){

        //NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
        //if(keywords!=null&&!"".equals(keywords)){
        //    builder.withQuery(QueryBuilders.multiMatchQuery(keywords, "goods_name", "caption"));
        //}
        //设置分页的信息,页码从0开始计算
        //builder.withPageable(PageRequest.of(pageIndex - 1, pageSize));
        //NativeSearchQuery searchQuery = builder.build();
        //条件查询分页，返回分页对象
        //AggregatedPage<Goods> page = elasticsearchTemplate.queryForPage(searchQuery, Goods.class);

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

        return new PageUtils<Goods>(pageIndex,pageSize,Integer.parseInt(page_withoutHighlight.getTotalElements()+""),page.getContent());
    }
}
