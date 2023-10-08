package work.twgj.aopdemo.controller;

import org.springframework.web.bind.annotation.*;
import work.twgj.aopdemo.annotation.Log;
import work.twgj.aopdemo.common.ApiResponse;

/**
 * @author weijie.zhu
 * @date 2023/10/8 9:54
 */
@RestController
@RequestMapping("/aop")
public class AopController {

    @Log("测试日志注解")
    @GetMapping("/aopTest")
    public ApiResponse aopTest(@RequestParam("username") String username){
        return ApiResponse.success();
    }

}
