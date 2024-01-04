package work.twgj.springsecuritydemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author weijie.zhu
 * @date 2023/12/1 10:26
 */
@RestController
@Slf4j
public class BrowserSecurityController {

    @GetMapping("/index")
    public Object index(){
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
