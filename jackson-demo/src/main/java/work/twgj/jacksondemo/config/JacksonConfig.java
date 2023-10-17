package work.twgj.jacksondemo.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;

/**
 * @author weijie.zhu
 * @date 2023/10/17 15:49
 */
@Configuration
public class JacksonConfig {

    /**
     * 配置了objectMapper可以让返回的对象中的对应字段，按照配置格式进行修改
     * */
    @Bean
    public ObjectMapper getObjectMapper(){
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH::mm:ss"));
        return mapper;
    }
}
