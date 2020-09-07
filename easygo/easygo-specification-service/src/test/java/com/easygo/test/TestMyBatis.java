package com.easygo.test;

import com.easygo.mapper.SpecificationMapper;
import com.easygo.pojo.Specification;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @BelongsProject: easygo
 * @BelongsPackage: com.bruce.test
 * @Author: bruceliu
 * @QQ:1241488705
 * @CreateTime: 2020-04-01 15:00
 * @Description: TODO
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestMyBatis {

    @Resource
    SpecificationMapper specificationMapper;

    @Test
    public void testId(){
        Specification specification=new Specification();
        specification.setSpec_name("测试返回主键");
        Integer count = specificationMapper.addSpecification(specification);
        if(count>0){
            System.out.println("新增成功,ID值是:"+specification.getId());
        }
    }
}
