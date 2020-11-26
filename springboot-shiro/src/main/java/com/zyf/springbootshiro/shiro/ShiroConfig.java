package com.zyf.springbootshiro.shiro;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
public class ShiroConfig {

    /**
     * 过滤器配置
     */
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 登录请求url
        shiroFilterFactoryBean.setLoginUrl("/login");
        // shiroFilterFactoryBean.setSuccessUrl("/login/welcome");
        // 权限不足跳转页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/login/fail");

        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        // anon过滤器：url可以匿名访问
        filterChainDefinitionMap.put("/login", "anon");
        filterChainDefinitionMap.put("/login/action", "anon");// 登录表单提交
        filterChainDefinitionMap.put("/hello/sh6", "anon");

        // authc过滤器：url认证通过才可以访问
        filterChainDefinitionMap.put("/admin/**", "authc");
        filterChainDefinitionMap.put("/user/**", "authc");

        // logout过滤器：登出，由shiro拦截，不需要写controller
        filterChainDefinitionMap.put("/logout", "logout");

        // perms过滤器：访问URL需要对应的权限：资源URL --> 权限数组
        filterChainDefinitionMap.put("/hello/sh1", "perms[hello:sh1]");
        filterChainDefinitionMap.put("/hello/sh2", "perms[hello:sh2]");
        filterChainDefinitionMap.put("/hello/sh3", "perms[hello:sh3]");

        // 主要这行代码必须放在所有权限设置的最后，不然会导致所有 url 都被拦截 剩余的都需要认证
        filterChainDefinitionMap.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;

    }

    /**
     * 安全管理器
     */
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager defaultSecurityManager = new DefaultWebSecurityManager();
        defaultSecurityManager.setRealm(customRealm());
        return defaultSecurityManager;
    }

    /**
     * 自定义realm
     */
    @Bean
    public CustomRealm customRealm() {
        CustomRealm customRealm = new CustomRealm();
        return customRealm;
    }

    // ------------------------------------------ 注解配置权限  开始-----------------------------------------------------
    /**
     * 开启对shior注解的支持
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

    /**
     * 开启AOP，对类代理
     */
    @Bean
    @ConditionalOnMissingBean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAAP = new DefaultAdvisorAutoProxyCreator();
        defaultAAP.setProxyTargetClass(true);
        return defaultAAP;
    }

    /**
     * 处理未授权的异常，返回自定义的错误页面
     */
    @Bean
    public SimpleMappingExceptionResolver simpleMappingExceptionResolver() {
        SimpleMappingExceptionResolver resolver = new SimpleMappingExceptionResolver();
        Properties properties = new Properties();
        // 未授权处理页
        properties.setProperty("UnauthorizedException", "/refuse.html");
        resolver.setExceptionMappings(properties);
        return resolver;
    }
    // ------------------------------------------ 注解配置权限  结束-----------------------------------------------------
}
