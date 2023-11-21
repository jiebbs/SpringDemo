package work.twgj.webfluxcruddemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

/**
 * 使用的是Reactive Mongo DB依赖，所有增删改查方法返回值类型为Flux或者Mono
 * 开启Reactive Mongo DB的相关配置，需要在Spring Boot启动类上添加@EnableReactiveMongoRepositories注解
 * */
@SpringBootApplication
@EnableReactiveMongoRepositories
public class WebfluxCrudDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebfluxCrudDemoApplication.class, args);
    }

}
