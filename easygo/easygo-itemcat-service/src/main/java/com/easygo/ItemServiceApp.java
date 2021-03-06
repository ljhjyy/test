package com.easygo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @BelongsProject: easygo
 * @BelongsPackage: com.easygo
 * @Author: bruceliu
 * @QQ:1241488705
 * @CreateTime: 2020-04-07 09:53
 * @Description: TODO
 */
@SpringBootApplication
@EnableEurekaClient
public class ItemServiceApp {

    public static void main(String[] args) {
        System.out.println("品牌服务9008.......");
        SpringApplication.run(ItemServiceApp.class,args);
    }
}
