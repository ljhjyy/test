package com.easygo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * @BelongsProject: easygo
 * @BelongsPackage: com.easygo
 * @Author: bruceliu
 * @QQ:1241488705
 * @CreateTime: 2020-04-13 15:28
 * @Description: 页面静态化服务工程，不负责连接数据库
 * 调用数据库中数据，生成静态页面
 */
@SpringBootApplication
@EnableEurekaClient //表示这个是一个Eureka客户端
@EnableFeignClients //开启Feign客户端
public class StaticPageServiceApp {

    public static void main(String[] args) {
        System.out.println("静态页面服务9011......");
        SpringApplication.run(StaticPageServiceApp.class,args);
    }
}
