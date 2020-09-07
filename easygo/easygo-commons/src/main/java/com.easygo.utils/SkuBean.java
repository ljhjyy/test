package com.easygo.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

/**
 * @BelongsProject: easygo
 * @BelongsPackage: com.easygo.utils
 * @Author: bruceliu
 * @QQ:1241488705
 * @CreateTime: 2020-04-14 14:58
 * @Description: TODO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SkuBean implements Serializable {

    private Long id;
    private String title;
    private Double price;
    private Map<String,Object> spec; //规格
}
