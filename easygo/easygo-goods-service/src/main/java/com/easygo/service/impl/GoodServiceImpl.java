package com.easygo.service.impl;

import com.easygo.mapper.GoodsMapper;
import com.easygo.pojo.Goods;
import com.easygo.service.GoodsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @BelongsProject: easygo
 * @BelongsPackage: com.easygo.service.impl
 * @Author: bruceliu
 * @QQ:1241488705
 * @CreateTime: 2020-04-10 16:28
 * @Description: TODO
 */
@Service
public class GoodServiceImpl implements GoodsService {

    @Resource
    GoodsMapper goodsMapper;

    @Override
    public List<Goods> getGoods(Integer is_marketable) {
        return goodsMapper.getGoods(is_marketable);
    }

    @Override
    public Goods getGoodsById(Long id) {
        return goodsMapper.getGoodsById(id);
    }
}
