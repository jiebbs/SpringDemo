package work.twgj.filterinterceptordemo.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import work.twgj.filterinterceptordemo.filter.TimeFilter2;
import work.twgj.filterinterceptordemo.interceptor.TimeInterceptor;

/**
 * @author weijie.zhu
 * @date 2023/11/3 14:35
 * 使用Configuration方式注册的filter会先于webFilter注解注册的filter被初始化
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public FilterRegistrationBean timeFilter2(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        TimeFilter2 filter2 = new TimeFilter2();
        filterRegistrationBean.setFilter(filter2);
        filterRegistrationBean.addUrlPatterns(
                "/*"
        );
        return filterRegistrationBean;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TimeInterceptor());
    }
}
