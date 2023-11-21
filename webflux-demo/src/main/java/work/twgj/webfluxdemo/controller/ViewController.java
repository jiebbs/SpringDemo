package work.twgj.webfluxdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author weijie.zhu
 * @date 2023/11/20 18:01
 */
@Controller
public class ViewController {

    @GetMapping("/flux")
    public String flux(){
        return "flux";
    }
}
