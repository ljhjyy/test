package com.easygo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @BelongsProject: easygo
 * @BelongsPackage: com.easygo
 * @Author: bruceliu
 * @QQ:1241488705
 * @CreateTime: 2020-04-16 14:46
 * @Description: TODO
 */
@SpringBootApplication
@EnableEurekaClient
public class UsersServiceApp {

    public static void main(String[] args) {
        System.out.println("会员服务9012.....");
        SpringApplication.run(UsersServiceApp.class,args);
    }
}
