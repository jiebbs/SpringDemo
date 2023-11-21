package work.twgj.resolverdemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import work.twgj.resolverdemo.common.ApiResponse;

import java.util.Properties;

/**
 * @author weijie.zhu
 * @date 2023/11/16 15:25
 */
@RestController
@RequestMapping
@Slf4j
public class TestController {

    @GetMapping(value = "/test1",consumes = "text/properties")
    public Properties test1(@RequestBody Properties properties){
        log.info("properties:{}",properties);
        return properties;
    }

    @GetMapping(value = "/test2",consumes = "text/properties")
    public Properties test2(Properties properties){
        log.info("properties:{}",properties);
        return properties;
    }
}
