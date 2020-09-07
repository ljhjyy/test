package com.easygo.mapper;

import com.easygo.pojo.Goods;
import com.easygo.pojo.GoodsDesc;
import org.apache.ibatis.annotations.Mapper;

/**
 * @BelongsProject: easygo
 * @BelongsPackage: com.easygo.mapper
 * @Author: bruceliu
 * @QQ:1241488705
 * @CreateTime: 2020-04-14 09:44
 * @Description: TODO
 */
@Mapper
public interface GoodsDescMapper {

    public GoodsDesc getGoodsDescById(Long id);
}
