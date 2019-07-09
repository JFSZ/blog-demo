package com.example.blog.config.shiro;

import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @ClassName ShiroConfig
 * @Author chenxue
 * @Description shiro 配置类
 * @Date 2019/7/4 15:29
 **/
@Configuration
public class ShiroConfig {
    @Bean
    public Realm realm(){
        return new MyRealm();
    }
    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition(){
        return new DefaultShiroFilterChainDefinition();
    }
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setLoginUrl("/login");
        shiroFilterFactoryBean.setUnauthorizedUrl("/unauthorized");
        //拦截器 系统默认拦截器
        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<>();
        //添加自己的过滤器
        Map<String,String> filterMap = new HashMap<>(16);
        //登录 退出 默认不拦截
        filterChainDefinitionMap.put("/login","anon");
        filterChainDefinitionMap.put("/logout","anon");
        filterChainDefinitionMap.put("/static/**","anon");
        filterChainDefinitionMap.put("/unauthorized","anon");
        filterChainDefinitionMap.put("/article/dataStatistics","authc,roles[admin]");
        filterChainDefinitionMap.put("/admin/**","authc,roles[admin]");
        //其他所有请求都需要验证
        filterChainDefinitionMap.put("/**","authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    public DefaultWebSecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm());
        return securityManager;
    }
    @Bean
    public CacheManager cacheManager(){
        return new MemoryConstrainedCacheManager();
    }
}
