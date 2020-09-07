package com.easygo.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.security.SecureRandom;
import java.util.List;

/**
 * @BelongsProject: easygo
 * @BelongsPackage: com.easygo.utils
 * @Author: bruceliu
 * @QQ:1241488705
 * @CreateTime: 2020-04-14 11:13
 * @Description: 规格对象
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttributeNameObject implements Serializable {

    private static final long serialVersionUID = -5558028184287353576L;
    private String attributeName;
    private List<String> attributeValue;

}
