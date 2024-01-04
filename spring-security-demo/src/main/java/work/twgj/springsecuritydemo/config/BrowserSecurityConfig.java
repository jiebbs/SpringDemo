package work.twgj.springsecuritydemo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import work.twgj.springsecuritydemo.handler.LoginAuthenticationSuccessHandler;
import work.twgj.springsecuritydemo.utils.LoginPasswordEncoder;

import javax.annotation.Resource;

/**
 * WebSecurityConfigurerAdapter是由Spring Security提供的Web应用安全配置的适配器
 * @author weijie.zhu
 * @date 2023/11/27 9:52
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin() // 表单验证（默认验证方式）
//                .httpBasic() // 弹窗验证
                .loginPage("/login.html") // 登录跳转 URL
                .loginProcessingUrl("/login") // 处理表单登录 URL
                .defaultSuccessUrl("/index")
                .and()
                .authorizeRequests() // 授权配置
                .antMatchers("/login.html").permitAll()
                .antMatchers("/js/**","/css/**","/images/**").permitAll()
                .anyRequest()  // 所有请求
                .authenticated() // 都需要认证
                .and()
                .csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new LoginPasswordEncoder());
    }
}
