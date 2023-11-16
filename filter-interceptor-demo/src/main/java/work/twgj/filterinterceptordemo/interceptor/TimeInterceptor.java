package work.twgj.filterinterceptordemo.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author weijie.zhu
 * @date 2023/11/3 14:45
 */
@Slf4j
public class TimeInterceptor implements HandlerInterceptor {

    /**
     * 预处理，controller方法执行前执行preHandle，其实这里就是可以用来判断当前进入拦截器的请求，要不要被拦截掉
     * @param request
     * @param response
     * @param handler
     * @return true:放行/不拦截，执行下一个拦截器，如果没有，执行controller方法，执行afterCompletion
     * @return false:不放行/拦截，不执行controller 不执行afterCompletion
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("处理拦截器之前...");
        request.setAttribute("startTime",System.currentTimeMillis());
        log.info("当前拦截到的类名：{}",((HandlerMethod)handler).getBean().getClass().getName());
        log.info("当前拦截到的方法名：{}",((HandlerMethod)handler).getMethod().getName());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("开始处理拦截器...");
        Long start = (Long)request.getAttribute("startTime");
        log.info("【拦截器耗时】：{} 毫秒",System.currentTimeMillis() - start);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("处理拦截器之后...");
        Long start = (Long)request.getAttribute("startTime");
        log.info("【拦截器耗时】：{} 毫秒",System.currentTimeMillis() - start);
        log.error("拦截器错误信息：{}",ex.getMessage());
    }
}
