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
 * @CreateTime: 2020-04-14 11:47
 * @Description: SKu的实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item implements Serializable {

    private static final long serialVersionUID = 6809026373832285568L;

    private Long id;
    private String title;
    private String sell_point;
    private Double price;
    private Long stock_count;
    private Long num;
    private String barcode;
    private String image;
    private Long categoryId;
    private String status;
    private String createTime;
    private String updateTime;
    private String item_sn;
    private Double cost_pirce;
    private Double market_price;
    private String is_default;
    private Long goods_id;
    private String seller_id;
    private String cart_thumbnail;
    private String category;
    private String brand;
    private String spec;
    private String seller;
}
