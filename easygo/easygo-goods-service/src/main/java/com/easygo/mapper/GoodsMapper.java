package com.easygo.mapper;

import com.easygo.pojo.Goods;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @BelongsProject: easygo
 * @BelongsPackage: com.easygo.mapper
 * @Author: bruceliu
 * @QQ:1241488705
 * @CreateTime: 2020-04-10 16:24
 * @Description: TODO
 */
@Mapper
public interface GoodsMapper {

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
