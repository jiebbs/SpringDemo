package work.twgj.asyncdemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import work.twgj.asyncdemo.service.TestService;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author weijie.zhu
 * @date 2023/11/16 16:57
 */
@RestController
@Slf4j
public class TestController {

    @Resource
    private TestService testService;

    @GetMapping("async")
    public void testAsync(){
        long start = System.currentTimeMillis();
        log.info("异步方法开始");

        testService.asyncMethod();

        log.info("异步方法结束");

        long end = System.currentTimeMillis();
        log.info("总耗时：{}",end-start);
    }

    @GetMapping("sync")
    public void testSync(){
        long start = System.currentTimeMillis();
        log.info("异步方法开始");

        testService.syncMethod();

        log.info("异步方法结束");

        long end = System.currentTimeMillis();
        log.info("总耗时：{}",end-start);
    }

    @GetMapping("/asyncFuture")
    public String testAsyncFutureMethod() throws ExecutionException, InterruptedException, TimeoutException {
        long start = System.currentTimeMillis();
        log.info("有返回值的异步方法开始");

        Future<String> future = testService.asyncFutureMethod();
        String result = future.get(60, TimeUnit.SECONDS);
        log.info("有返回值的异步方法结束");

        long end = System.currentTimeMillis();
        log.info("总耗时：{}",end-start);
        return result;
    }

}
