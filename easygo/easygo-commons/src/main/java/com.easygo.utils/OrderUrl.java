package com.easygo.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @BelongsProject: easygo
 * @BelongsPackage: com.easygo.utils
 * @Author: bruceliu
 * @QQ:1241488705
 * @CreateTime: 2020-04-14 10:14
 * @Description: 扩展对象
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderUrl implements Serializable {

    private static final long serialVersionUID = -7261707654827273755L;

    private Integer order;
    private String url;
}
