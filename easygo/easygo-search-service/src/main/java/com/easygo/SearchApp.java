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
 * @CreateTime: 2020-04-10 15:10
 * @Description: TODO
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients //开启Feign客户端
public class SearchApp {

    public static void main(String[] args) {
        System.out.println("搜索服务9009.......");
        SpringApplication.run(SearchApp.class,args);
    }
}
