package com.easygo.service.impl;

import com.easygo.mapper.SpecificationOptionMapper;
import com.easygo.pojo.SpecificationOption;
import com.easygo.service.SpecificationOptionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @BelongsProject: easygo
 * @BelongsPackage: com.easygo.service.impl
 * @Author: bruceliu
 * @QQ:1241488705
 * @CreateTime: 2020-04-01 16:09
 * @Description: TODO
 */
@Service
public class SpecificationOptionServiceImpl implements SpecificationOptionService {

    @Resource
    SpecificationOptionMapper specificationOptionMapper;

    @Override
    public int addSpecificationOption(SpecificationOption option) {
        return specificationOptionMapper.addSpecificationOption(option);
    }

    @Override
    public List<SpecificationOption> getSpecificationOptionsBySpecificationId(Integer specificationId) {
        return specificationOptionMapper.getSpecificationOptionsBySpecificationId(specificationId);
    }

    @Override
    public int deleteSpecificationOptions(int spec_id) {
        return specificationOptionMapper.deleteSpecificationOptions(spec_id);
    }
}
