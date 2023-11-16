package work.twgj.autoconfigdemo.annotation;

import java.lang.annotation.*;

/**
 * @author weijie.zhu
 * @date 2023/11/8 16:49
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@FirstLevelService
public @interface SecondLevelService {
    String value() default "";
}
