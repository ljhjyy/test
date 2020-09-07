package com.easygo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Period;

/**
 * @BelongsProject: easygo
 * @BelongsPackage: com.easygo.pojo
 * @Author: bruceliu
 * @QQ:1241488705
 * @CreateTime: 2020-04-01 11:30
 * @Description: TODO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpecificationOption implements Serializable {

    private static final long serialVersionUID = 4522866958820850948L;

    private Integer id;
    private String option_name;

    private Specification specification; //外键对象

    //private Integer spec_id;
    private Integer orders;
    private Integer del;
}
