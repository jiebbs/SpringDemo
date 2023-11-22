package work.twgj.shirodemo.handler;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author weijie.zhu
 * @date 2023/11/22 16:23
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 处理shiro权限校验异常
     * */
    @ExceptionHandler(AuthorizationException.class)
    public String authorizationExceptionHandler(AuthorizationException e){
        return "403";
    }
}
