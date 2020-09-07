package com.easygo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @BelongsProject: easygo
 * @BelongsPackage: com.easygo
 * @Author: bruceliu
 * @QQ:1241488705
 * @CreateTime: 2020-03-27 11:12
 * @Description: TODO
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaApp {

    public static void main(String[] args) {
        System.out.println("注册中心9001...");
        SpringApplication.run(EurekaApp.class,args);
    }
}
