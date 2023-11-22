package work.twgj.shirodemo.config;


import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import cn.hutool.core.codec.Base64;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import work.twgj.shirodemo.bean.ShiroRealm;
import work.twgj.shirodemo.listener.ShiroSessionListener;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 *  在Spring Boot中集成Shiro进行用户的认证过程主要可以归纳为以下三点：
 *
 *  1、定义一个ShiroConfig，然后配置SecurityManager Bean，SecurityManager为Shiro的安全管理器，管理着所有Subject；
 *
 *  2、在ShiroConfig中配置ShiroFilterFactoryBean，其为Shiro过滤器工厂类，依赖于SecurityManager；
 *
 *  3、自定义Realm实现，Realm包含doGetAuthorizationInfo()和doGetAuthenticationInfo()方法，因为本文只涉及用户认证，所以只实现doGetAuthenticationInfo()方法。
 *
 * Shiro配置
 * @author weijie.zhu
 * @date 2023/11/21 16:02
 */
@Configuration
@Slf4j
public class ShiroConfig {

    /**
     *
     * filterChain基于短路机制，即最先匹配原则
     * /user/**=anon
     * /user/aa=authc 永远不会执行
     * */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 设置SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 登录url
        shiroFilterFactoryBean.setLoginUrl("/login");
        // 登录成功url
        shiroFilterFactoryBean.setSuccessUrl("/index");
        // 未授权url
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");


        LinkedHashMap<String,String> filterChainDefinitionMap = new LinkedHashMap<>();

        // 定义filterChain 静态资源不拦截
        filterChainDefinitionMap.put("/css/**","anon");
        filterChainDefinitionMap.put("/static/js/**","anon");
        filterChainDefinitionMap.put("/fonts/**","anon");
        filterChainDefinitionMap.put("/img/**","anon");
        // 配置druid数据源监控页面不拦截
        filterChainDefinitionMap.put("/druid/**","anon");
        // 配置退出过滤器,具体退出代码Shiro已实现了该逻辑
        filterChainDefinitionMap.put("/logout","logout");
        filterChainDefinitionMap.put("/","anon");
        // 除了以上所有url都必须认证通过才可以进行访问，未通过认证自动访问LoginUrl
//        filterChainDefinitionMap.put("/**","authc");
        // user指的是用户认证通过或者配置了Remember Me记住用户登录状态后可访问。
        filterChainDefinitionMap.put("/**","user");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    /**
     * 注意不要选错 DefaultSecurityManager
     * */
    @Bean
    public DefaultWebSecurityManager securityManager(ShiroRealm shiroRealm,
                                                     CookieRememberMeManager cookieRememberMeManager,
                                                     RedisCacheManager redisCacheManager,
                                                     SessionManager sessionManager){
        // 配置securityManager
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(shiroRealm);
        // 配置cookies
        securityManager.setRememberMeManager(cookieRememberMeManager);
        // 配置redis缓存
        securityManager.setCacheManager(redisCacheManager);
        // 配置session manager
        securityManager.setSessionManager(sessionManager);
        return securityManager;
    }

    @Bean
    public ShiroRealm shiroRealm(){
        return new ShiroRealm();
    }

    /**
     * cookies对象
     * */
    @Bean
    public SimpleCookie simpleCookie(){
        // 设置cookie名称，对应login.html页面的<input type="checkbox" name="rememberMe"/>
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        // 设置cookies的过期时间为一天，单位是秒
        simpleCookie.setMaxAge(86400);
        return simpleCookie;
    }

    @Bean
    public CookieRememberMeManager cookieRememberMeManager(SimpleCookie simpleCookie){
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(simpleCookie);
        // rememberMe cookie加密的密钥
        cookieRememberMeManager.setCipherKey(Base64.decode("4AvVhmFLUs0KTA3Kprsdag=="));
        return cookieRememberMeManager;
    }

    /**
     * 开启Shiro的注解(如@RequiresRoles,@RequiresPermissions),需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证
     * 配置以下两个bean(DefaultAdvisorAutoProxyCreator和AuthorizationAttributeSourceAdvisor)即可实现此功能
     * */
    @Bean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);
        return creator;
    }

    /**
     * 开启aop注解支持
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

    /**
     * 配置shiro-redis缓存
     * */
    @Bean
    public RedisManager redisManager(){
        return new RedisManager();
    }

    @Bean
    public RedisCacheManager redisCacheManager(RedisManager redisManager){
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager);
        return redisCacheManager;
    }

    /**
     * ShiroConfig中配置该方言标签,声明bean
     * */
    @Bean
    public ShiroDialect shiroDialect(){
        return new ShiroDialect();
    }

    /**
     * 基于redis缓存方案的 SessionDAO
     * Shiro在线会话管理
     * */
    @Bean
    public SessionDAO sessionDAO(RedisManager redisManager){
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager);
        return redisSessionDAO;
    }

    /**
     * SessionDao通过org.apache.shiro.session.mgt.SessionManager进行管理
     * */
    @Bean
    public SessionManager sessionManager(SessionDAO sessionDAO){
        DefaultWebSessionManager securityManager = new DefaultWebSessionManager();
        List<SessionListener> listeners = new ArrayList<>();
        listeners.add(new ShiroSessionListener());
        securityManager.setSessionListeners(listeners);
        securityManager.setSessionDAO(sessionDAO);
        return securityManager;
    }
}
