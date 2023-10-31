package work.twgj.xssdemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import work.twgj.xssdemo.common.ApiResponse;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author weijie.zhu
 * @date 2023/10/31 11:20
 */
@RestController
@RequestMapping("/xss")
@Slf4j
public class XssController {

    //springmvc绑定参数原理：
    //普通参数绑定调用：HttpServletRequestWrapper.getParameterValues() 方法
    @GetMapping("/xssNoAnotation/{id}")
    public ApiResponse xssNoAnotation(String xss,String name){
        log.info("xss1NoAnotation,xss:{},name:{}",xss,name);
        return ApiResponse.success();
    }

    //普通参数(携带注解)绑定调用：HttpServletRequestWrapper.getParameterValues() 方法
    @RequestMapping("/xssRequestParam/{id}")
    public ApiResponse xssRequestParam(@RequestParam("xss")String xss,@RequestParam("name")String name){
        log.info("xssRequestParam,xss:{},name:{}",xss,name);
        return ApiResponse.success();
    }

    //普通参数Path路径参数(携带注解)绑定调用：未调用 HttpServletRequestWrapper 中的方法
    @RequestMapping("/xssPathVariable/{id}")
    public ApiResponse xssPathVariable(@PathVariable("id")String path){
        log.info("xssPathVariable,path:{}",path);
        return ApiResponse.success();
    }

    //绑定Map集合(携带注解@RequestParam)：调用 HttpServletRequestWrapper.getParameterMap 中的方法
    @RequestMapping("/xssRequestParamMap/{id}")
    public ApiResponse xssRequestParamMap(@RequestParam(required = false) Map<String,Object> param){
        log.info("xssRequestParamMap,paramMap:{}",param);
        return ApiResponse.success();
    }

    //绑定Map集合(携带注解@RequestBody)：调用HttpServletRequests实例获取参数 HttpServletRequestWrapper.getInputStream 中的方法
    @RequestMapping("/xssRequestBody/{id}")
    public ApiResponse xssRequestBody(@RequestBody(required = false) Map<String,Object> param){
        log.info("xssRequestBody,paramMap:{}",param);
        return ApiResponse.success();
    }

    //HttpServletRequests实例获取参数 HttpServletRequestWrapper.getParameter 中的方法
    @RequestMapping("/xssHttpServletRequest/{id}")
    public ApiResponse xssHttpServletRequest(HttpServletRequest request){
        String xss = request.getParameter("xss");
        log.info("xssHttpServletRequest,xss:{}",xss);
        return ApiResponse.success();
    }

    //请求例子(postman)：http://localhost:8080/tomcat/user/xss/123?xss=a
    //postman中body --- raw
//    {
//        "name":"王某",
//        "age":12,
//        "param":"<script>alert(1)<script/>",
//        "xss":"<button>test</button>"
//        }
    //@RequestParam(required = false)Map<String,Object> param1
    //与
    //@RequestBody(required = false) Map<String,Object> param2区别：
    //前者仅封装get请求的url中的参数，后者仅封装post请求中body的数据

    //结果：param1 = {xss=a}
    //结果：param2 = {test=name, param=alert(1), name=王某, xss=test, age=12}
}
