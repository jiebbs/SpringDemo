package work.twgj.filterinterceptordemo.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;


/**
 * @author weijie.zhu
 * @date 2023/11/3 14:23
 * Component注解让TimeFilter成为Spring上下文中的一个Bean，
 * WebFilter注解的urlPatterns属性配置了哪些请求可以进入该过滤器，/*表示所有请求。
 */
@Component
@WebFilter(urlPatterns = {"/*"})
@Slf4j
public class TimeFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("开始执行过滤器...");
        long start = System.currentTimeMillis();
        filterChain.doFilter(servletRequest,servletResponse);
        log.info("过滤器销毁时间：{} 毫秒",System.currentTimeMillis() - start);
        log.info("结束执行过滤器...");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("初始化过滤器...");
    }

    @Override
    public void destroy() {
        log.info("销毁过滤器...");
    }
}
