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
 * @CreateTime: 2020-04-21 14:54
 * @Description: TODO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address implements Serializable {

    private static final long serialVersionUID = 8804249462059114463L;
    private Long id;
    private String username;
    private String province_id;
    private String city_id;
    private String town_id;
    private String mobile;
    private String address;
    private String contact;
    private String notes;
    private String is_default;
    private String create_date;
    private String alias;
}
