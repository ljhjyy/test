package com.easygo.service.impl;

import com.easygo.mapper.GoodsDescMapper;
import com.easygo.pojo.GoodsDesc;
import com.easygo.service.GoodsDescService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @BelongsProject: easygo
 * @BelongsPackage: com.easygo.service.impl
 * @Author: bruceliu
 * @QQ:1241488705
 * @CreateTime: 2020-04-14 09:47
 * @Description: TODO
 */
@Service
public class GoodsDescServiceImpl implements GoodsDescService {

    @Resource
    GoodsDescMapper goodsDescMapper;

    @Override
    public GoodsDesc getGoodsDescById(Long id) {
        return goodsDescMapper.getGoodsDescById(id);
    }
}
