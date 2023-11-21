package work.twgj.kafkademo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import work.twgj.kafkademo.common.ApiResponse;
import work.twgj.kafkademo.domain.Message;

import javax.annotation.Resource;


/**
 * @author weijie.zhu
 * @date 2023/11/17 9:42
 */
@RestController
@Slf4j
public class KafkaController {

//    @Resource
//    private KafkaTemplate<String,String> kafkaTemplate;
    @Resource
    private KafkaTemplate<String,Message> kafkaTemplate;

    @GetMapping("/send/{message}")
    public void send(@PathVariable("message")String message){
//        kafkaTemplate.send("test",message);
        kafkaTemplate.send("test",new Message("twgj",message));
    }

    @GetMapping("/sendWithResult/{message}")
    public void sendWithResult(@PathVariable("message")String message){
//        ListenableFuture<SendResult<String,String>> future = kafkaTemplate.send("test",message);
        ListenableFuture<SendResult<String,Message>> future = kafkaTemplate.send("test",new Message("twgj",message));
        future.addCallback(new ListenableFutureCallback<SendResult<String, Message>>() {
            @Override
            public void onFailure(Throwable throwable) {
                log.info("消息发送失败：{},原因：{}",message, throwable.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, Message> stringStringSendResult) {
                log.info("消息发送成功：{},offset：[{}]",message, stringStringSendResult.getRecordMetadata().offset());
            }
        });
    }
}
