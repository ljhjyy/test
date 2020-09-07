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
 * @CreateTime: 2020-03-27 10:13
 * @Description: TODO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Brand implements Serializable {

    private static final long serialVersionUID = 5268136551205423998L;
    private Integer id;
    private String name;
    private String image;
    private String first_char;
    private Integer del;

    //扩展属性
    private Boolean flag=false; //默认是false
}
