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
 * @CreateTime: 2020-04-07 09:49
 * @Description: TODO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemCat implements Serializable {

    private static final long serialVersionUID = 6148557286788686037L;

    private  Integer id;
    private Integer parent_id;
    private String name;
    private TypeTemplate typeTemplate; //外键对应  typeTemplate表  type_id
    private Integer del;
}
