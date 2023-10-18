package work.twgj.testingdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import work.twgj.testingdemo.service.EchoService;

import javax.annotation.Resource;

/**
 * @author weijie.zhu
 * @date 2023/10/18 14:19
 */
@RestController
@RequestMapping
public class EchoController {

    @Resource
    private EchoService echoService;

    @GetMapping("/echo/")
    public String echo(String name) {
        return echoService.echo(name);
    }
}
