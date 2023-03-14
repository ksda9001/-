package com.shop.config;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    @Bean
    @ConditionalOnMissingBean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAAP = new DefaultAdvisorAutoProxyCreator();
        defaultAAP.setProxyTargetClass(true);
        return defaultAAP;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    @Bean
    public Realm userRealm() {
        return new Realm();
    }

    @Bean
    public DefaultWebSecurityManager securityManager(@Qualifier("userRealm") Realm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);

        return securityManager;
    }

    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();

        // 配置角色校验路径
        //        chainDefinition.addPathDefinition("/admin/**", "authc, roles[admin]");

        // 配置较为详细的权限校验路径
        //        chainDefinition.addPathDefinition("/docs/**", "authc, perms[document:read]");
        // 配置默认放行策略（不拦截资源配置）
        Map<String, String> map = new HashMap<>();
        map.put("/**/*.css", "anon");
        map.put("/**/*.js", "anon");
        map.put("/**/*.png", "anon");
        map.put("/**/*.jpg", "anon");
        map.put("/**/*.jpeg", "anon");
        map.put("/**/*.mp4", "anon");
        map.put("/**/*.eot", "anon");
        map.put("/**/*.svg", "anon");
        map.put("/**/*.ttf", "anon");
        map.put("/**/*.woff", "anon");
        map.put("/**/*.woff2", "anon");
        map.put("/", "anon");
//        map.put("/index", "anon");
        map.put("/login", "anon");
        map.put("/register", "anon");
//        map.put("/druid/*","anon");
//        map.put("admin/editState/*","anon");
        chainDefinition.addPathDefinitions(map);
        // 配置需要登录认证的路径拦截
        chainDefinition.addPathDefinition("/**", "authc");
        return chainDefinition;
    }
}