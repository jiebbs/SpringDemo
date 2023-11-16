package work.twgj.springapplicationdemo.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @author weijie.zhu
 * @date 2023/11/16 11:19
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
public class HelloSpringApplicationRunListener implements SpringApplicationRunListener {

    public HelloSpringApplicationRunListener(SpringApplication springApplication, String[] args) {
    }

    /**
     * Spring 应用刚启动
     * */
    @Override
    public void starting() {
        log.info("HelloApplicationRunListener starting...");
    }

    /**
     * ConfigurableEnvironment 准备完毕，允许将其调整
     * */
    @Override
    public void environmentPrepared(ConfigurableEnvironment environment) {

    }

    /**
     * ConfigurableApplicationContext 准备完毕，允许将其调整
     * */
    @Override
    public void contextPrepared(ConfigurableApplicationContext context) {

    }

    /**
     * ConfigurableApplicationContext 已装载，但仍未启动
     * */
    @Override
    public void contextLoaded(ConfigurableApplicationContext context) {

    }

    /**
     * ConfigurableApplicationContext 已启动，此时 Spring Bean 已初始化完成
     * */
    @Override
    public void started(ConfigurableApplicationContext context) {

    }

    /**
     * Spring 应用正在运行
     * */
    @Override
    public void running(ConfigurableApplicationContext context) {

    }

    /**
     * 	Spring 应用运行失败
     * */
    @Override
    public void failed(ConfigurableApplicationContext context, Throwable exception) {

    }
}
