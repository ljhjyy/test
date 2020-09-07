package com.easygo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @BelongsProject: easygo
 * @BelongsPackage: com.easygo
 * @Author: bruceliu
 * @QQ:1241488705
 * @CreateTime: 2020-04-01 11:35
 * @Description: TODO
 */
@SpringBootApplication
@EnableEurekaClient
public class SpecificationServiceApp {

    public static void main(String[] args) {
        System.out.println("规格服务9006.......");
        SpringApplication.run(SpecificationServiceApp.class,args);
    }

}
