package work.twgj.exceptiondemo.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import work.twgj.exceptiondemo.exception.UserNotExistException;

import java.util.HashMap;
import java.util.Map;

/**
 * @author weijie.zhu
 * @date 2023/11/3 11:58
 */
@ControllerAdvice
public class ControllerExceptionHandler {

    /**
     * 在此处定义了的错误处理，会在controller抛出指定错误时，返回对应的响应信息
     * */
    @ExceptionHandler(UserNotExistException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String,Object> handleUserNotExistException(UserNotExistException e){
        Map<String,Object> map = new HashMap<>();
        map.put("id",e.getId());
        map.put("message",e.getMessage());
        return map;
    }
}
