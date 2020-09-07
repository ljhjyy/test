package com.easygo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sun.rmi.runtime.Log;

import java.io.Serializable;

/**
 * @BelongsProject: easygo
 * @BelongsPackage: com.easygo.pojo
 * @Author: bruceliu
 * @QQ:1241488705
 * @CreateTime: 2020-04-14 09:41
 * @Description: 商品描述表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoodsDesc implements Serializable {

    private static final long serialVersionUID = 8886400365364975161L;

    private Long goods_id; //商品ID
    private String introduction; //商品详情介绍
    private String specification_items; //商品规格属性
    private String custom_attribute_items;//扩展属性
    private String package_list; //包装列表
    private String sale_service; //售后详情
    private String item_images; //商品图片集合

}
