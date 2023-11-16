package work.twgj.autoconfigdemo.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import work.twgj.autoconfigdemo.annotation.EnableHelloWorld;

/**
 * @author weijie.zhu
 * @date 2023/11/9 17:10
 */
@Configuration
@EnableHelloWorld
@ConditionalOnProperty(name = "helloWorld",havingValue = "true")
public class HelloAutoConfiguration {

}
