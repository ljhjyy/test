package com.easygo.service;

import com.easygo.pojo.SpecificationOption;

import java.util.List;

/**
 * @BelongsProject: easygo
 * @BelongsPackage: com.easygo.service
 * @Author: bruceliu
 * @QQ:1241488705
 * @CreateTime: 2020-04-01 16:09
 * @Description: TODO
 */
public interface SpecificationOptionService {

    //新增
    public int addSpecificationOption(SpecificationOption option);

    public List<SpecificationOption> getSpecificationOptionsBySpecificationId(Integer specificationId);;

    public int deleteSpecificationOptions(int spec_id);

}
