package com.easygo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @BelongsProject: easygo
 * @BelongsPackage: com.easygo
 * @Author: bruceliu
 * @QQ:1241488705
 * @CreateTime: 2020-04-03 09:50
 * @Description: TODO
 */
@SpringBootApplication
@EnableEurekaClient
public class TypeTemplateServiceApp {

    public static void main(String[] args) {
        System.out.println("模板服务9007.......");
        SpringApplication.run(TypeTemplateServiceApp.class,args);
    }
}
