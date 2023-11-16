package work.twgj.autoconfigdemo;

import cn.hutool.extra.spring.SpringUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import work.twgj.autoconfigdemo.annotation.EnableHelloWorld;
import work.twgj.autoconfigdemo.service.TestService;

import javax.security.auth.login.Configuration;

//@SpringBootApplication
@EnableAutoConfiguration
public class AutoConfigDemoApplication {

    public static void main(String[] args) {
//        SpringApplication.run(AutoConfigDemoApplication.class, args);

        ConfigurableApplicationContext configurableApplicationContext =
                new SpringApplicationBuilder(AutoConfigDemoApplication.class)
                        .web(WebApplicationType.NONE)
                        .run(args);

//        TestService testService = configurableApplicationContext.getBean(TestService.class);
//        System.out.println(testService);

        String hello = configurableApplicationContext.getBean("hello",String.class);
        System.out.println(hello);
    }

}
