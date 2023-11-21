package work.twgj.shirodemo.config;


import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import work.twgj.shirodemo.bean.ShiroRealm;
import work.twgj.shirodemo.service.UserService;

import java.util.LinkedHashMap;

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
        filterChainDefinitionMap.put("/js/**","anon");
        filterChainDefinitionMap.put("/fonts/**","anon");
        filterChainDefinitionMap.put("/img/**","anon");
        // 配置druid数据源监控页面不拦截
        filterChainDefinitionMap.put("/druid/**","anon");
        // 配置退出过滤器,具体退出代码Shiro已实现了该逻辑
        filterChainDefinitionMap.put("/logout","logout");
        filterChainDefinitionMap.put("/","anon");
        // 除了以上所有url都必须认证通过才可以进行访问，未通过认证自动访问LoginUrl
        filterChainDefinitionMap.put("/**","authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    /**
     * 注意不要选错 DefaultSecurityManager
     * */
    @Bean
    public DefaultWebSecurityManager securityManager(ShiroRealm shiroRealm){
        // 配置securityManager
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(shiroRealm);
        return securityManager;
    }

    @Bean
    public ShiroRealm shiroRealm(){
        return new ShiroRealm();
    }
}
