package work.twgj.logbackdemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author weijie.zhu
 * @date 2023/10/26 18:34
 */
@RestController
@RequestMapping("/logback")
@Slf4j
public class LogbackController {

    @PostMapping("/log")
    public void log(){
        log.info("这是日志");
    }
}
