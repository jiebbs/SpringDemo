package work.twgj.autoconfigdemo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author weijie.zhu
 * @date 2023/11/8 17:02
 */
//@Configuration
public class HelloConfiguration {

    @Bean
    public String hello(){
        return "hello world";
    }
}
