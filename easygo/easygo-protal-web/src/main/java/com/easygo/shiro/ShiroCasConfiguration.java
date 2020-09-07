package com.easygo.shiro;

import com.easygo.config.CasConfig;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.cas.CasFilter;
import org.apache.shiro.cas.CasSubjectFactory;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Shiro集成CAS配置
 * CasConfig.class 自己配置属性读取类
 */
@Configuration
@EnableConfigurationProperties(CasConfig.class)
public class ShiroCasConfiguration {

    //cas和shiro整合过滤器名字
    private static final String CAS_FILTER = "casFilter";

    /**
     * 01-Shiro的缓存管理器
     * 使用缓存是ehcache
     * ehcache-shiro.xml
     * @return
     */
    @Bean
    public EhCacheManager ehcacheManager(){
        EhCacheManager ehcacheManager = new EhCacheManager();
        ehcacheManager.setCacheManagerConfigFile("classpath:ehcache-shiro.xml");
        return ehcacheManager;
    }

    /**
     * 02-整合MyShiroCasRealm
     * @param ehCacheManager
     * @param casConfig
     * @return
     */
    @Bean(name = "myShiroCasRealm")
    public MyShiroCasRealm myShiroRealm(EhCacheManager ehCacheManager, CasConfig casConfig){
        MyShiroCasRealm realm = new MyShiroCasRealm();
        realm.setCacheManager(ehCacheManager);
        realm.setCasServerUrlPrefix(casConfig.getCasServerUrlPrefix());
        realm.setCasService(casConfig.getCasService());
        return realm;
    }

    /**
     * 03-注册shiroFilter过滤器，让Shiro拦截Cas控制器中的请求
     */
    @Bean
    public FilterRegistrationBean filterRegistrationBean(){
        FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
        filterRegistration.setFilter(new DelegatingFilterProxy("shiroFilter"));
        // 该值缺省为false，表示生命周期有SpringApplicationContext管理，设置为true则表示由ServletContainer管理
        filterRegistration.addInitParameter("targetFilterLifecycle", "true");
        filterRegistration.setEnabled(true);
        //默认拦截所有请求
        filterRegistration.addUrlPatterns("/*");
        return filterRegistration;
    }

    /**
     * Shiro的生命周期
     * @return
     */
    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        return new LifecycleBeanPostProcessor();
    }

    /**
     * Shiro的的AOP增强工具，Shiro有几种授权方式：注解授权的AOP增强
     * @return
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);
        return creator;
    }

    /**
     * Shiro安全管理器
     * @param realm
     * @return
     */
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager defaultWebSecurityManager(MyShiroCasRealm realm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm);
        securityManager.setCacheManager(ehcacheManager());
        // 指定SubjectFactory
        securityManager.setSubjectFactory(new CasSubjectFactory());
        return securityManager;
    }

    /**
     * 开启Shiro的授权的时候注解支持
     * @RequiresRoles("admin")
     * @RequiresPermissions("user:delete")
     *
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager, CasConfig casConfig, CasFilter casFilter){
        ShiroFilterFactoryBean factoryBean = new MyShiroFilterFactoryBean();
        factoryBean.setSecurityManager(securityManager);
        factoryBean.setLoginUrl(casConfig.getLocalServerLoginUrl());
        //登录成功之后，跳转的地址控制器地址
        factoryBean.setSuccessUrl("/member_index");
        //收钱失败跳转的地址，没有权限提示路径
        factoryBean.setUnauthorizedUrl("/403");
        // 添加casFilter到shiroFilter中
        Map<String, Filter> filterMap = new HashMap<String, Filter>(1);
        filterMap.put(CAS_FILTER, casFilter);
        factoryBean.setFilters(filterMap);
        loadShiroFilterChain(factoryBean, casConfig);
        return factoryBean;
    }

    /**
     * 加载ShiroFilter权限控制规则 重点，哪些拦截，哪些放行
     */
    private void loadShiroFilterChain(ShiroFilterFactoryBean factoryBean, CasConfig casConfig) {
        /**下面这些规则配置最好配置到配置文件中*/
        Map<String, String> filterChainMap = new LinkedHashMap<String, String>();
        filterChainMap.put(casConfig.getCasFilterUrlPattern(), CAS_FILTER);//shiro集成cas后，首先添加该规则

        //可以把配置配置到配置文件中，不要写死
        //注册页面 不拦截
        filterChainMap.put("/page_regist", "anon");
        //添加购物车无所谓登录否，放开
        filterChainMap.put("/cart_add", "anon");
        filterChainMap.put("/cart_show", "anon");
        //测试阶段
        filterChainMap.put("/pay", "anon");
        filterChainMap.put("/queryPayStatus", "anon");
        filterChainMap.put("/payFail", "anon");

        //测试分布式事务
        filterChainMap.put("/xiaDan", "anon");
        filterChainMap.put("/ttl", "anon");

        filterChainMap.put("/paySuccess/**", "anon");
        //放开
        filterChainMap.put("/cart_delete", "anon");
        // 所有的都拦截，都需认证
        filterChainMap.put("/**", "authc");
        factoryBean.setFilterChainDefinitionMap(filterChainMap);
    }

    /**
     * CAS过滤器
     */
    @Bean
    public CasFilter casFilter(CasConfig casConfig){
        CasFilter casFilter = new CasFilter();
        casFilter.setName(CAS_FILTER);
        casFilter.setEnabled(true);
        casFilter.setFailureUrl(casConfig.getLocalServerLoginUrl());
        return casFilter;
    }


}