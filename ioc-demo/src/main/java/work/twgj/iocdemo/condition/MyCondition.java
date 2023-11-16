package work.twgj.iocdemo.condition;


import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author weijie.zhu
 * @date 2023/11/9 14:37
 */
public class MyCondition implements Condition {

    /**
     * ConditionContext：上下文信息；
     * AnnotatedTypeMetadata：注解信息
     * */
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        String osName = conditionContext.getEnvironment().getProperty("os.name");
        // 系统名称包含Windows才加载
        return osName != null && osName.contains("Windows");
    }
}
