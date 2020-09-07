package com.easygo.test;

import com.easygo.mapper.BrandMapper;
import com.easygo.pojo.Brand;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @BelongsProject: easygo
 * @BelongsPackage: com.easygo.test
 * @Author: bruceliu
 * @QQ:1241488705
 * @CreateTime: 2020-03-27 10:22
 * @Description: TODO
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestBrand {

    @Resource
    BrandMapper brandMapper;

    @Test
    public void testBrands(){
        System.out.println(brandMapper);
        List<Brand> brands = brandMapper.getBrands();
        for (Brand brand : brands) {
            System.out.println(brand);
        }
    }

    @Test
    public void testGetByid(){
        Brand brand = brandMapper.getBrandById(1);
        System.out.println(brand);
    }

    @Test
    public void testAdd(){
        Brand brand=new Brand();
        brand.setName("测试品牌");
        brand.setFirst_char("C");
        brand.setImage("23423423.jpg");
        int count = brandMapper.addBrand(brand);
        System.out.println(count>0?"新增成功":"新增失败");
    }

    @Test
    public void testUpdate(){
        Brand brand = brandMapper.getBrandById(1);
        System.out.println(brand);
        brand.setName("更新的测试");
        int count = brandMapper.updateBrand(brand);
        System.out.println(count>0?"更新成功":"更新失败");
    }

    @Test
    public void testDeleteSome(){
        String ids="10,11,12";
        int count = brandMapper.deleteSome(ids);
        System.out.println(count>0?"删除成功":"删除失败");
    }

    @Test
    public void testDeleteSome1(){
        Integer[] ids={20,21,22};
        int count = brandMapper.deleteSome1(ids);
        System.out.println(count>0?"删除成功":"删除失败");
    }

    @Test
    public void testgetTotalCount(){
        int totalCount = brandMapper.getTotalCount();
        System.out.println("查询总数是:"+totalCount);
    }

    @Test
    public void testPage(){
        int pageIndex=1; //当前页码
        int pageSize=5; //每页大小
        Map<String,Object> map=new HashMap<>();
        map.put("pageStart",(pageIndex-1)*pageSize);
        map.put("pageSize",pageSize);
        List<Brand> brands = brandMapper.getPageBrands(map);
        for (Brand brand : brands) {
            System.out.println(brand);
        }
    }
}
