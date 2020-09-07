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
 * @CreateTime: 2020-03-30 14:45
 * @Description: 广告
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Content implements Serializable {

    private static final long serialVersionUID = -1959640528219022204L;

    private Integer id;
    private ContentCategory contentCategory; //外键字段
    private String title;
    private String url;
    private String pic;
    private String status = "0"; //默认值，如果用户没有勾选，默认是0 如果勾选1
    private Integer sort_order;
    private Integer del;
}
