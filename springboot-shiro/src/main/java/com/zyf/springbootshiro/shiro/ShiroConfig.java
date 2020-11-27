package com.zyf.springbootshiro.shiro;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

/**
 * shiro配置类
 */
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
        filterChainDefinitionMap.put("/hello/anonymous", "anon");

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
//        defaultSecurityManager.setCacheManager(ehCacheManager()); // 缓存管理器 ehcache
        defaultSecurityManager.setCacheManager(redisCacheManager()); // 缓存管理器 redis
        return defaultSecurityManager;
    }

    /**
     * 自定义realm
     */
    @Bean
    public CustomRealm customRealm() {
        CustomRealm customRealm = new CustomRealm();
//        customRealm.setCacheManager(ehCacheManager()); // 缓存管理器 ehcache
        customRealm.setCacheManager(redisCacheManager()); // 缓存管理器 redis
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

    /**
     * shiro模板方言
     *
     * 权限控制控件显隐
     * @return
     */
    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }

    // ------------------------------------------ 缓存管理器 redis  开始-------------------------------------------------
    /**
     * shiro缓存管理器
     * @return
     */
    @Bean
    public RedisCacheManager redisCacheManager(){
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        //redis中针对不同用户缓存
//        redisCacheManager.setPrincipalIdFieldName("username");
        //用户权限信息缓存时间，秒
        redisCacheManager.setExpire(10);
        return redisCacheManager;
    }

    @Bean
    public RedisManager redisManager(){
        RedisManager redisManager = new RedisManager();
        redisManager.setHost("127.0.0.1:6379");
//        redisManager.set.setPort(6379);
//        redisManager.setPassword("123456");
        return redisManager;
    }

    // ------------------------------------------ 缓存管理器 redis  结束-------------------------------------------------

    // ------------------------------------------ 缓存管理器 ehcache  开始-----------------------------------------------
    /**
     * 缓存管理器 chcache
     * @return
     */
    @Bean
    public EhCacheManager ehCacheManager(){
        EhCacheManager cacheManager = new EhCacheManager();
        cacheManager.setCacheManagerConfigFile("classpath:shiro-ehcache.xml");
        return cacheManager;
    }

    /**
     * 设置为共享模式
     * @return
     */
    @Bean
    public EhCacheManagerFactoryBean ehCacheManagerFactoryBean() {
        EhCacheManagerFactoryBean cacheManagerFactoryBean = new EhCacheManagerFactoryBean();
        cacheManagerFactoryBean.setShared(true);
        return cacheManagerFactoryBean;
    }
    // ------------------------------------------ 缓存管理器 ehcache  结束-----------------------------------------------
}
