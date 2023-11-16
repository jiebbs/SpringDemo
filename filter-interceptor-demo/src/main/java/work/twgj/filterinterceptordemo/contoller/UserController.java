package work.twgj.filterinterceptordemo.contoller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author weijie.zhu
 * @date 2023/11/3 14:21
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @GetMapping("/getUser1/{id:\\d+}")
    public void getUser1(@PathVariable("id")String id){
        log.info("id:{}",id);
    }

    @GetMapping("/getUser2/{id:\\d+}")
    public void getUser2(@PathVariable("id")String id){
        log.info("id:{}",id);
        throw new RuntimeException("运行时错误");
    }
}
