package work.twgj.springbootdevtoolsdemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import work.twgj.springbootdevtoolsdemo.common.ApiResponse;

/**
 * @author weijie.zhu
 * @date 2023/10/26 17:32
 */
@RestController
@RequestMapping("/hello")
@Slf4j
public class HelloController {

    @PostMapping("/say")
    public ApiResponse say(){
        return ApiResponse.success("say hello 2");
    }
}
