package com.easygo.mapper;

import com.easygo.pojo.SpecificationOption;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @BelongsProject: easygo
 * @BelongsPackage: com.easygo.mapper
 * @Author: bruceliu
 * @QQ:1241488705
 * @CreateTime: 2020-04-01 16:06
 * @Description: TODO
 */
@Mapper
public interface SpecificationOptionMapper {

    //新增
    public int addSpecificationOption(SpecificationOption option);

    public List<SpecificationOption> getSpecificationOptionsBySpecificationId(Integer specificationId);

    public int updateSpecificationOption(SpecificationOption option);

    public int deleteSpecificationOptions(int spec_id);
}
