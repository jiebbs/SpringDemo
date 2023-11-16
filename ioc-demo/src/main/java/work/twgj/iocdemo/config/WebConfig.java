package work.twgj.iocdemo.config;

import org.springframework.context.annotation.*;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import work.twgj.iocdemo.FactoryBean.LionFactoryBean;
import work.twgj.iocdemo.bean.Hello;
import work.twgj.iocdemo.bean.User;
import work.twgj.iocdemo.condition.MyCondition;
import work.twgj.iocdemo.filter.MyTypeFilter;
import work.twgj.iocdemo.importSelector.MyImportSelector;
import work.twgj.iocdemo.registrar.MyImportBeanDefinitionRegistrar;

/**
 * @author weijie.zhu
 * @date 2023/11/9 9:29
 */
@Configuration
//@Import({Hello.class})
//@Import({MyImportSelector.class})
@Import({MyImportBeanDefinitionRegistrar.class})
//@ComponentScan(value = "work.twgj.iocdemo.bean"
//        ,includeFilters = {
//            @Filter(type = FilterType.ASSIGNABLE_TYPE,classes = {Service.class})
//        },
//        excludeFilters = {
//            @Filter(type = FilterType.ANNOTATION,classes = {Controller.class, Repository.class}), // 指定注解类型进行排除
//                @Filter(type = FilterType.ASSIGNABLE_TYPE,classes = {User.class}) // 根据指定类型排除
//        },
//        excludeFilters = {
//                @Filter(type = FilterType.CUSTOM,classes = {MyTypeFilter.class}) // 指定自定义规则
//        },
//        useDefaultFilters = false
//)
public class WebConfig {

        @Bean
        @Conditional(MyCondition.class) // 条件加载
        public User user(){
                System.out.println("往IOC容器中注册user bean");
                return new User("twgj","18");
        }

        @Bean
        public LionFactoryBean lionFactoryBean(){
                return new LionFactoryBean();
        }
}
