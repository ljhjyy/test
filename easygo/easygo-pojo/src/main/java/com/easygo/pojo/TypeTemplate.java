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
 * @CreateTime: 2020-04-03 09:45
 * @Description: 模板管理  规格 品牌
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TypeTemplate implements Serializable {

    private static final long serialVersionUID = -1642254272758790944L;

    private Integer id;
    private String name;
    private String spec_ids;
    private String brand_ids;
    private String custom_attribute_items;
    private Integer del;

}
