package com.easygo.service.impl;

/**
 * @BelongsProject: easygo
 * @BelongsPackage: com.easygo.service.impl
 * @Author: bruceliu
 * @QQ:1241488705
 * @CreateTime: 2020-04-24 11:23
 * @Description: TODO
 */
import com.codingapi.tx.config.service.TxManagerTxUrlService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 添加从注册中心获取url；注意通过注解放入容器。
 */
@Service
public class TxManagerTxUrlServiceImpl implements TxManagerTxUrlService{

    @Value("${tm.manager.url}")
    private String url;

    @Override
    public String getTxUrl() {
        return url;
    }
}
