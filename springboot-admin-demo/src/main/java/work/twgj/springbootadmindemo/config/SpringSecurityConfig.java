package work.twgj.springbootadmindemo.config;

import de.codecentric.boot.admin.server.config.AdminServerProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

/**
 * 配置springboot admin 必须要配spring security 不然登录会报405
 * @author weijie.zhu
 * @date 2023/10/26 9:41
 */
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 配置文件中的 spring.boot.admin.context-path 属性值
     * 下列中涉及到该参数的都是为了处理对 context-path 的支持
     */
    private final String contextPath;

    public SpringSecurityConfig(AdminServerProperties adminServerProperties) {
        this.contextPath = adminServerProperties.getContextPath();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
        successHandler.setTargetUrlParameter("redirectTo");
        successHandler.setDefaultTargetUrl(contextPath + "/");

        http.authorizeRequests()
                // 对静态资源与登录url放行
                .antMatchers(contextPath + "/asserts/**").permitAll()
                .antMatchers(contextPath + "/login").permitAll()
                // 其他请求需要登录
                .anyRequest().authenticated()
                .and()
                // 配置登录url,主要是为了支持 spring.boot.admin.context-path 属性
                .formLogin().loginPage(contextPath + "/login").successHandler(successHandler).and()
                // 配置注销url
                .logout().logoutUrl(contextPath + "/logout").and()
                .httpBasic().and()
                .csrf()
                // 配置 csrf,在前端使用 cookie 保存 csrf 参数
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                // 对 instances,actuator url忽略 csrf 验证
                .ignoringAntMatchers(
                        contextPath + "/instances",
                        contextPath + "/actuator/**"
                );

    }
}
