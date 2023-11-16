package work.twgj.springapplicationdemo.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * Spring Boot也是通过Spring的工厂方法来实例化ApplicationListener的实现类，并进行排序。
 * 监听的是ApplicationEvent接口的实现类
 *
 * @author weijie.zhu
 * @date 2023/11/16 10:35
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
public class ContextClosedEventListener implements ApplicationListener<ContextClosedEvent> {

    @Override
    public void onApplicationEvent(ContextClosedEvent contextClosedEvent) {
        log.info("AfterContextCloseEvent:{}",contextClosedEvent.getApplicationContext().getId());
    }
}
