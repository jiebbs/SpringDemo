package work.twgj.springapplicationdemo.initializer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * 利用Spring工厂加载机制，实例化ApplicationContextInitializer实现类，并进行排序。
 * 所以我们可以通过实现ApplicationContextInitializer接口用于在Spring Boot应用初始化之前执行一些自定义操作。
 *
 * @author weijie.zhu
 * @date 2023/11/16 10:24
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
public class HelloApplicationContextInitializer implements ApplicationContextInitializer {


    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
      log.info("ConfigurableApplicationContext.id - " + configurableApplicationContext.getId());
    }
}
