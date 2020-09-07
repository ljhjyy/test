package com.easygo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RestController;

/**
 * @BelongsProject: easygo
 * @BelongsPackage: com.easygo
 * @Author: bruceliu
 * @QQ:1241488705
 * @CreateTime: 2020-03-27 11:21
 * @Description: TODO
 */
@SpringBootApplication
@EnableEurekaClient //表示这个是一个Eureka客户端
@EnableFeignClients //开启Feign客户端
public class AdminWebAPP {

    public static void main(String[] args) {
        System.out.println("运营商管理WEB,9002......");
        SpringApplication.run(AdminWebAPP.class,args);
    }
}
