package com.easygo.shiro;

import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cas.CasRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

/**
 * @BelongsProject: SpringBoot-CAS-Shiro
 * @BelongsPackage: com.bruce.shiro
 * @Author: bruceliu
 * @QQ:1241488705
 * @CreateTime: 2020-04-18 11:38
 * @Description: Shiro的Realm 作用：连接数据库，读取数据库的授权和认证信息
 * 传统的Realm中有2个方法：认证的方法 授权的方法
 * <p>
 * 今日realm继承，重写其中的一个授权的方法，认证的方法没有，因为CAS服务器负责了认证
 */
public class MyShiroCasRealm extends CasRealm {

    /**
     * 01-给用用户授权
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String loginName = (String) super.getAvailablePrincipal(principals);
        //模拟查询数据库用户的权限【5张表  用户表 角色表 用户角色表 权限表 角色权限表】
        System.out.println("模拟查询数据库用户的权限【5张表  用户表 角色表 用户角色表 权限表 角色权限表】");
        System.out.println("查询到了用户的权限和角色，模拟......");
        // 权限信息对象info，用来存放查出的用户的所有的角色及权限
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        //下面也就可以查询模拟的用户的角色
        Set<String> roles = new HashSet<String>();
        roles.add("admin");
        roles.add("normal");

        //下面就是模拟的查询用户的权限
        Set<String> perms = new HashSet<String>();
        perms.add("del");
        perms.add("query");
        perms.add("user:query");

        info.setRoles(roles);
        info.setStringPermissions(perms);

        System.out.println("授权完成:这里是授权的地方..............................");
        return info;
    }
    // 返回null将会导致用户访问任何被拦截的请求时都会自动跳转到unauthorizedUrl指定的地址
}

