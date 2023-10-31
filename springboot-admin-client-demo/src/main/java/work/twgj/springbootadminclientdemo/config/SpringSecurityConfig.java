package work.twgj.springbootadminclientdemo.config;

import org.springframework.boot.actuate.autoconfigure.endpoint.web.WebEndpointProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author weijie.zhu
 * @date 2023/10/26 10:07
 */
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 配置文件中的 management.endpoints.web.base-path 属性值
     * 这里只对 web 端点做登录验证
     */
    private final String endpointsBasePath;

    public SpringSecurityConfig(WebEndpointProperties webEndpointProperties) {
        this.endpointsBasePath = webEndpointProperties.getBasePath();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // web端点需要登录
                .antMatchers(endpointsBasePath + "/**").authenticated()
                // 其他请求进行忽略
                .anyRequest().permitAll()
                // 使用 httpBasic 进行登录验证,这是 spring-boot-admin server 的验证方式
                .and().httpBasic()
        // 如果不配置 formLogin,不会重定向到登录页,因为不需要登录页面
        // .and().formLogin()
        ;
        // 忽略对端点的 csrf 验证,或者直接关闭 csrf,http.csrf().disable();
        http.csrf().ignoringAntMatchers(endpointsBasePath + "/**");
    }
}
