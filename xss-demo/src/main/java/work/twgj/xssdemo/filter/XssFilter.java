package work.twgj.xssdemo.filter;

import work.twgj.xssdemo.wrapper.XssHttpServletRequestWrapper;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author weijie.zhu
 * @date 2023/10/31 15:52
 */
@WebFilter(urlPatterns = "/**")
public class XssFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest)servletRequest;
        // 对请求进行转义
        // 使用自定义的包装类对请求进行包装
        XssHttpServletRequestWrapper xssHttpServletRequestWrapper = new XssHttpServletRequestWrapper(httpServletRequest);
        filterChain.doFilter(xssHttpServletRequestWrapper,servletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
