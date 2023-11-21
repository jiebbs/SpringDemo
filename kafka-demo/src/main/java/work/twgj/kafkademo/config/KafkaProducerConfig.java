package work.twgj.kafkademo.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import work.twgj.kafkademo.domain.Message;

import java.util.HashMap;
import java.util.Map;

/**
 * kafka 生产者配置
 * @author weijie.zhu
 * @date 2023/11/17 9:36
 */
@Configuration
public class KafkaProducerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    public ProducerFactory<String,String> producerFactory(){
        Map<String,Object> configProps = new HashMap<>(3);
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrapServers);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        // 解析字符串消息
//        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class);
        // 解析对象消息
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

//    @Bean
//    public KafkaTemplate<String,String> kafkaTemplate(ProducerFactory producerFactory){
//        return new KafkaTemplate<>(producerFactory);
//    }

    @Bean
    public KafkaTemplate<String, Message> kafkaTemplate(ProducerFactory producerFactory){
      return new KafkaTemplate<String, Message>(producerFactory);
    }
}
