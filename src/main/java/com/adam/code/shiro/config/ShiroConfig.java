package com.adam.code.shiro.config;

import com.adam.code.shiro.realm.MyRealm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Shiro配置类
 * @author VAIO-adam
 *
 * Filter Chaind 定义说明
 * 1.一个URL可以配置多个filter 使用逗号分割
 * 2.当设置多个过滤器时，全部验证通过
 * 3.部分过滤器可以指定参数
 */

@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        //  必须设置securityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        //  如果不设置，默认会自动寻找web工程目录下的login.jsp页面
        shiroFilterFactoryBean.setLoginUrl("/");

        //  拦截器
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();

        //  过滤链定义 从上往下执行 已被将 /** 放在最下面
        //  authc:   所有的URL必须认证通过才可以访问
        //  anon:   所有的URL都可以匿名访问
        filterChainDefinitionMap.put("/", "anon");
        filterChainDefinitionMap.put("/static/*", "anon");
        filterChainDefinitionMap.put("/ueditor/*", "anon");
        filterChainDefinitionMap.put("/user/register.html", "anon");
        filterChainDefinitionMap.put("/user/login.html", "anon");
        filterChainDefinitionMap.put("/user/register", "anon");

        //  配置退出过滤器 其中的具体的退出代码shiro已经帮我们实现了
        filterChainDefinitionMap.put("/user/logout", "logout");

        filterChainDefinitionMap.put("/**", "anthc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return shiroFilterFactoryBean;
    }

    @Bean
    public SecurityManager securityManager () {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //  设置realm
        securityManager.setRealm(myRealm());
        return securityManager;
    }

    /**
     * 身份认证realm
     * @return
     */
    @Bean
    public MyRealm myRealm() {
        MyRealm myRealm = new MyRealm();
        return myRealm;
    }

    /**
     * shiro 生命周期
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 开启shiro的注解（如 @RequiresRoles, @RequiresPermissions）,需借助springAOP扫描使用shiro注解的类
     * 配置以下两个bean即可实现
     */
    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {

        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);

        return advisorAutoProxyCreator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {

        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());

        return authorizationAttributeSourceAdvisor;
    }
}
