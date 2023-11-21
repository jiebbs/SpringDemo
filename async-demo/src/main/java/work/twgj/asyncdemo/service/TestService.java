package work.twgj.asyncdemo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author weijie.zhu
 * @date 2023/11/16 16:58
 */
@Service
@Slf4j
public class TestService {

    @Async("asyncThreadPoolExecutor")
    public void asyncMethod(){
        sleep();
        log.info("异步方法内部线程名称：{}",Thread.currentThread().getName());
    }

    @Async("asyncThreadPoolExecutor")
    public Future<String> asyncFutureMethod(){
        sleep();
        log.info("有结果返回的异步方法,异步方法内部线程名称：{}",Thread.currentThread().getName());
        return new AsyncResult<>("hello async");
    }

    public void syncMethod(){
        sleep();
    }

    private void sleep(){
        try {
            TimeUnit.SECONDS.sleep(2);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
