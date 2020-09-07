package com.easygo.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 这个类是shiro整合cas的时候需要的一些配置
 * 申明的一些变量
 */
@SpringBootConfiguration
@ConfigurationProperties(prefix = "cas")
public class CasConfig {

    //这些属性就是配置文件中配置的属性，但是类中给了一些默认值，但是如果说application.properties文件中配置过
    //那么就使用自己配置的，如果没有配置，那么使用默认值
    private String casServerUrlPrefix = "https://server.fable.com:8443/cas";
    private String casServerLoginUrl = casServerUrlPrefix + "/login";
    private String casServerLogoutUrl = casServerUrlPrefix + "/logout";
    private String localServerUrlPrefix = "http://client1.fable.com:8081/aaaa";
    private String casFilterUrlPattern = "/shiro-cas";
    private String localServerLoginUrl = casServerLoginUrl + "?service=" + localServerUrlPrefix + casFilterUrlPattern;

    public String getCasServerUrlPrefix() {
        return casServerUrlPrefix;
    }

    public void setCasServerUrlPrefix(String casServerUrlPrefix) {
        this.casServerUrlPrefix = casServerUrlPrefix;
    }

    public String getCasServerLoginUrl() {
        return casServerLoginUrl;
    }

    public void setCasServerLoginUrl(String casServerLoginUrl) {
        this.casServerLoginUrl = casServerLoginUrl;
    }

    public String getCasServerLogoutUrl() {
        return casServerLogoutUrl;
    }

    public void setCasServerLogoutUrl(String casServerLogoutUrl) {
        this.casServerLogoutUrl = casServerLogoutUrl;
    }

    public String getLocalServerUrlPrefix() {
        return localServerUrlPrefix;
    }

    public void setLocalServerUrlPrefix(String localServerUrlPrefix) {
        this.localServerUrlPrefix = localServerUrlPrefix;
    }

    public String getCasFilterUrlPattern() {
        return casFilterUrlPattern;
    }

    public void setCasFilterUrlPattern(String casFilterUrlPattern) {
        this.casFilterUrlPattern = casFilterUrlPattern;
    }

    public String getLocalServerLoginUrl() {
        return localServerLoginUrl;
    }

    public void setLocalServerLoginUrl(String localServerLoginUrl) {
        this.localServerLoginUrl = localServerLoginUrl;
    }

    public String getCasService() {
        return localServerUrlPrefix + casFilterUrlPattern;
    }

}
