package work.twgj.filterinterceptordemo.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;


/**
 * @author weijie.zhu
 * @date 2023/11/3 14:23
 */
@Slf4j
public class TimeFilter2 implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("开始执行过滤器2...");
        long start = System.currentTimeMillis();
        filterChain.doFilter(servletRequest,servletResponse);
        log.info("过滤器销毁时间：{} 毫秒",System.currentTimeMillis() - start);
        log.info("结束执行过滤器2...");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("初始化过滤器2...");
    }

    @Override
    public void destroy() {
        log.info("销毁过滤器2...");
    }
}
