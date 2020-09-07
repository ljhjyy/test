package com.easygo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @BelongsProject: easygo
 * @BelongsPackage: com.easygo
 * @Author: bruceliu
 * @QQ:1241488705
 * @CreateTime: 2020-04-22 15:54
 * @Description: TODO
 */
@SpringBootApplication
@EnableEurekaClient
public class PayServiceApp {

    public static void main(String[] args) {
        System.out.println("支付服务9015....");
        SpringApplication.run(PayServiceApp.class,args);
    }
}
