package work.twgj.iocdemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import work.twgj.iocdemo.bean.User;
import work.twgj.iocdemo.config.WebConfig;
import work.twgj.iocdemo.service.CalculateService;

import java.util.Arrays;

@Slf4j
@SpringBootApplication
public class IocDemoApplication {

    public static void main(String[] args) {
//        SpringApplication.run(IocDemoApplication.class, args);
       ConfigurableApplicationContext configurableApplicationContext = new SpringApplicationBuilder(IocDemoApplication.class)
               .web(WebApplicationType.NONE)
               .profiles("java7")
               .run(args);


        // 通过注解配置注入bean到ioc中,如果注解配置类没有声明扫描包的起始路径，就以配置类所在包进行扫描，这样在其他包中使用
        // @component注解的类，也不会被扫描到ioc中
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(WebConfig.class);
//        User user  = applicationContext.getBean(User.class);
//        log.info(user);

        // 获取ioc容器中与指定类型相同bean的名称
        String[] beanNames = applicationContext.getBeanNamesForType(User.class);
        Arrays.stream(beanNames).forEach(log::info);

        String[] beanNames2 = applicationContext.getBeanDefinitionNames();
        Arrays.stream(beanNames2).forEach(log::info);

        Object o1 = applicationContext.getBean("user");
        Object o2 = applicationContext.getBean("user");
        log.info("o1==o2 ---> {}",o1==o2);

        CalculateService calculateService = configurableApplicationContext.getBean(CalculateService.class);
        Integer result = calculateService.sum(1,2,3);
        log.info(result.toString());

        Object Lion = applicationContext.getBean("&lionFactoryBean");
        log.info(Lion.getClass().getName());
    }

}
