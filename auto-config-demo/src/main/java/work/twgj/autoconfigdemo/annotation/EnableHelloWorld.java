package work.twgj.autoconfigdemo.annotation;

import org.springframework.context.annotation.Import;
import work.twgj.autoconfigdemo.configuration.HelloConfiguration;
import work.twgj.autoconfigdemo.selector.HelloImportSelector;

import java.lang.annotation.*;

/**
 *
 * @author weijie.zhu
 * @date 2023/11/8 17:05
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
//@Import({HelloConfiguration.class}) // 引入指定配置类
@Import({HelloImportSelector.class})
public @interface EnableHelloWorld {

}
