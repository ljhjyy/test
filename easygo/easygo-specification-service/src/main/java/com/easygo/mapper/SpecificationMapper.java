package com.easygo.mapper;

import com.easygo.pojo.Specification;
import org.apache.ibatis.annotations.Mapper;
import org.omg.CORBA.INTERNAL;

import java.util.List;
import java.util.Map;

/**
 * @BelongsProject: easygo
 * @BelongsPackage: com.easygo.mapper
 * @Author: bruceliu
 * @QQ:1241488705
 * @CreateTime: 2020-04-01 11:37
 * @Description: TODO
 */
@Mapper
public interface SpecificationMapper {

    public Integer totalCount(Map<String,Object> map);

    public List<Specification> getSpecificationPage(Map<String,Object> map);

    public Integer addSpecification(Specification specification);

    public Specification getSpecificationById(Integer id);

    public Integer updateSpecificationById(Specification specification);

    public List<Specification> getSpecifications();
}
