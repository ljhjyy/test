package com.easygo.service;

import com.easygo.pojo.Goods;

import java.util.List;

/**
 * @BelongsProject: easygo
 * @BelongsPackage: com.easygo.service
 * @Author: bruceliu
 * @QQ:1241488705
 * @CreateTime: 2020-04-10 16:27
 * @Description: TODO
 */
public interface GoodsService {

    /*
     * 查询所有的上架的商品数据
     */
    public List<Goods> getGoods(Integer is_marketable);

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    public Goods getGoodsById(Long id);
}
