package work.twgj.springapplicationdemo;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Spring Boot 2.0后，应用可以分为下面三种类型：
 *
 * WebApplicationType.NONE：非WEB类型；
 *
 * WebApplicationType.REACTIVE：Web Reactive类型；
 *
 * WebApplicationType.SERVLET：Web Servlet类型。
 * */
//@SpringBootApplication
public class SpringApplicationDemoApplication {

    public static void main(String[] args) {
        //        SpringApplication.run(SpringApplicationDemoApplication.class, args);

//        SpringApplication springApplication = new SpringApplication(SpringApplicationDemoApplication.class);
//        springApplication.setBannerMode(Banner.Mode.OFF);
//        springApplication.setWebApplicationType(WebApplicationType.NONE);
//        springApplication.setAdditionalProfiles("dev");
//        springApplication.run(args);

//        ConfigurableApplicationContext applicationContext =
//                new SpringApplicationBuilder(SpringApplicationDemoApplication.class)
//                        .bannerMode(Banner.Mode.OFF)
//                        .web(WebApplicationType.NONE)
//                        .profiles("dev")
//                        .run(args);

        SpringApplication application = new SpringApplication(ApplicationResource.class);
        application.run(args);
    }

    @SpringBootApplication
    public static class ApplicationResource{

    }
}
