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
 * @CreateTime: 2020-04-02 21:30
 * @Description: TODO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JsonBean implements Serializable {

    private static final long serialVersionUID = -4966837857704664487L;
    private Integer id;
    private String text;
}
