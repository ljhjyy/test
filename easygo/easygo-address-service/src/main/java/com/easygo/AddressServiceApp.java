package com.easygo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @BelongsProject: easygo
 * @BelongsPackage: com.easygo
 * @Author: bruceliu
 * @QQ:1241488705
 * @CreateTime: 2020-04-21 14:53
 * @Description: TODO
 */
@SpringBootApplication
@EnableEurekaClient
public class AddressServiceApp {

    public static void main(String[] args) {
        System.out.println("会员地址服务9013.....");
        SpringApplication.run(AddressServiceApp.class,args);
    }
}
