package com.easygo.shiro;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.mgt.FilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.mgt.WebSecurityManager;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springframework.beans.factory.BeanInitializationException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * @BelongsProject: SpringBoot-CAS-Shiro
 * @BelongsPackage: com.bruce.shiro
 * @Author: bruceliu
 * @QQ:1241488705
 * @CreateTime: 2020-04-18 11:47
 * @Description: 默认的CAS和Shiro整合的时候，内部使用的是过滤器拦截，默认会拦截所有的静态资源
 * 那么为了项目中不拦截静态资源，可以将静态资源放行
 *
 * 静态资源放行
 */
public class MyShiroFilterFactoryBean extends ShiroFilterFactoryBean {

    // ShiroFilter将直接忽略的请求,要放行的静态资源
    private Set<String> ignoreExt;

    //无参数的构造方法
    public MyShiroFilterFactoryBean() {
        super();
        ignoreExt = new HashSet<String>();
        ignoreExt.add(".jpg");
        ignoreExt.add(".png");
        ignoreExt.add(".gif");
        ignoreExt.add(".bmp");
        ignoreExt.add(".js");
        ignoreExt.add(".css");
        //.......
        //静态资源不要拦截......
    }


    @Override
    protected AbstractShiroFilter createInstance() throws Exception {
        SecurityManager securityManager = getSecurityManager();
        if (securityManager == null) {
            throw new BeanInitializationException("SecurityManager property must be set.");
        }

        if (!(securityManager instanceof WebSecurityManager)) {
            throw new BeanInitializationException("The security manager does not implement the WebSecurityManager interface.");
        }

        PathMatchingFilterChainResolver chainResolver = new PathMatchingFilterChainResolver();
        FilterChainManager chainManager = createFilterChainManager();
        chainResolver.setFilterChainManager(chainManager);
        return new MySpringShiroFilter((WebSecurityManager) securityManager, chainResolver);
    }

    private class MySpringShiroFilter extends AbstractShiroFilter {
        public MySpringShiroFilter(
                WebSecurityManager securityManager, PathMatchingFilterChainResolver chainResolver) {
            super();
            if (securityManager == null) {
                throw new IllegalArgumentException("WebSecurityManager property cannot be null.");
            }
            setSecurityManager(securityManager);
            if (chainResolver != null) {
                setFilterChainResolver(chainResolver);
            }
        }

        /**
         * 修改了源码
         * @param servletRequest
         * @param servletResponse
         * @param chain
         * @throws ServletException
         * @throws IOException
         */
        @Override
        protected void doFilterInternal(ServletRequest servletRequest, ServletResponse servletResponse,
                                        FilterChain chain)
                throws ServletException, IOException {
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            String str = request.getRequestURI().toLowerCase();
            boolean flag = true;
            int idx = 0;
            ///js/jquery-1.8.2.min.js
            if ((idx = str.lastIndexOf(".")) > 0) {
                str = str.substring(idx);
                if (ignoreExt.contains(str.toLowerCase())) {
                    //不拦截
                    flag = false;
                }
            }
            if (flag) {
                super.doFilterInternal(servletRequest, servletResponse, chain);
            } else {
                chain.doFilter(servletRequest, servletResponse);
            }
        }
    }
}

