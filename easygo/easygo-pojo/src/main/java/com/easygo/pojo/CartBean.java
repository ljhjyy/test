package com.easygo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @BelongsProject: easygo
 * @BelongsPackage: com.easygo.pojo
 * @Author: bruceliu
 * @QQ:1241488705
 * @CreateTime: 2020-04-20 15:30
 * @Description: 购物车实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartBean implements Serializable {

    private static final long serialVersionUID = -607638293288355614L;

    private Long item_id; //skuID
    private Long goods_id;
    private Long order_id; //订单ID
    private String title;
    private Double price;
    private Long num;
    private Double total_fee;
    private String pic_path;
    private String seller_id;

}
