package work.twgj.exceptiondemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import work.twgj.exceptiondemo.exception.UserNotExistException;

/**
 * Spring Boot对异常的处理有一套默认的机制：当应用中产生异常时，Spring Boot根据发送请求头中的accept是否包含text/html来分别返回不同的响应信息。
 * 当从浏览器地址栏中访问应用接口时，请求头中的accept便会包含text/html信息，产生异常时，Spring Boot通过org.springframework.web.servlet.ModelAndView对象来装载异常信息，
 * 并以HTML的格式返回；而当从客户端访问应用接口产生异常时（客户端访问时，请求头中的accept不包含text/html），Spring Boot则以JSON的格式返回异常信息。
 * @author weijie.zhu
 * @date 2023/11/3 11:37
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/getUser1/{id:\\d+}")
    public void getUser1(@PathVariable("id") String id){
        // 通过浏览器访问时，若在resources下的resources的error文件夹中有对应的错误代码的页面如500.html,系统就会默认返回该页面
        throw new RuntimeException("user not exist");
    }

    @GetMapping("/getUser2/{id:\\d+}")
    public void getUser2(@PathVariable("id") String id){
        // 通过浏览器访问时，若在resources下的resources的error文件夹中有对应的错误代码的页面如500.html,系统就会默认返回该页面
        throw new UserNotExistException(id);
    }



}
