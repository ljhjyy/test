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
 * @CreateTime: 2020-04-03 10:25
 * @Description: TODO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JsonObject implements Serializable {

    private static final long serialVersionUID = 5082605674447175837L;

    private Integer id;
    private String text;
}
