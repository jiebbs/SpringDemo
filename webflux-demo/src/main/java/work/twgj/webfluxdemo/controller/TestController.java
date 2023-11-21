package work.twgj.webfluxdemo.controller;

import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author weijie.zhu
 * @date 2023/11/20 14:36
 */
@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {

    @GetMapping("/sync")
    public String sync(){
        long start = System.currentTimeMillis();
        log.info("sync method start");
        String result = this.execute();
        long cost = System.currentTimeMillis() - start;
        log.info("cost:{} ms",cost);
        log.info("sync method end:");
        return result;
    }

    @GetMapping("/async/mono")
    public Mono<String> asyncMono(){
        long start = System.currentTimeMillis();
        log.info("async method start");
        Mono<String> result = Mono.fromSupplier(this::execute);
        long cost = System.currentTimeMillis() - start;
        log.info("cost:{} ms",cost);
        log.info("async method end");
        return result;
    }

    private String execute(){
        try{
            TimeUnit.SECONDS.sleep(2);
        }catch (Exception e){
            log.error("执行异常e:{}",e);
        }
        return "hello";
    }

    /**
     * 返回值类型为Flux的时候，它是一个数据流，不是一次性数据包，服务端会不断地（假如Flux数据长度大于1）往客户端发送数据。
     * 这时，客户端不会关闭连接，会一直等着服务器发过来的新的数据流。这种模式称为Server-Sent Events。
     * */
    @GetMapping(value = "/async/flux",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> asyncFlux(){
        log.info("async flux method start");
        Flux<String> result = Flux.fromStream(IntStream.range(1,5).mapToObj(i->{
            try{
                TimeUnit.SECONDS.sleep(2);
            }catch (Exception e){
                log.error("服务器异常e：{}",e);
            }
            return "int value:" + i;
        }));
        log.info("async flux method end");
        return result;
    }
}
