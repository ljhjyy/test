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
 * @CreateTime: 2020-04-01 11:28
 * @Description: TODO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Specification implements Serializable {

    private static final long serialVersionUID = 8837759180331415520L;

    private Integer id;
    private String spec_name;
    private Integer del;

    //扩展属性
    private Boolean flag=false;

}
