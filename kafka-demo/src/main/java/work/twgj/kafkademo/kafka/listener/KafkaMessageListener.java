package work.twgj.kafkademo.kafka.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import work.twgj.kafkademo.domain.Message;

/**
 * kafka消息监听
 * @author weijie.zhu
 * @date 2023/11/17 16:04
 */
@Component
@Slf4j
public class KafkaMessageListener {

    /**
     * @Header注解来获取当前消息来自哪个分区（partitions）:
     * @KafkaListener来指定只接收来自特定分区的消息
     * */
//    @KafkaListener(groupId = "test-consumer",topicPartitions = @TopicPartition(topic = "test",partitionOffsets = @PartitionOffset(partition = "0",initialOffset = "0")))
    // 上面这种写法可以简化为下面这种写法
    @KafkaListener(groupId = "test-consumer",topicPartitions = @TopicPartition(topic = "test",partitions = {"0","1"}))
    public void listen(@Payload Message message,
                       @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition){
        log.info("消费者监听到消息：{},partition:{}",message,partition);
    }
}
