package work.twgj.crossorigindemo.contoller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import work.twgj.crossorigindemo.common.ApiResponse;

/**
 * @author weijie.zhu
 * @date 2023/11/16 16:33
 */
@RestController
@RequestMapping
public class TestController {

    // 跨域注解
    // @CrossOrigin(value = "*")
    @GetMapping("/test1")
    public ApiResponse test1(){
        return ApiResponse.success();
    }

    @GetMapping("/test2")
    public ApiResponse test2(){
        return ApiResponse.success();
    }
}
