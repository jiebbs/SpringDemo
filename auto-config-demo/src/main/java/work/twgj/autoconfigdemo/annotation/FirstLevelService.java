package work.twgj.autoconfigdemo.annotation;

import org.springframework.stereotype.Service;

import java.lang.annotation.*;

/**
 * @author weijie.zhu
 * @date 2023/11/8 16:29
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Service
public @interface FirstLevelService {
    String value() default "";
}
