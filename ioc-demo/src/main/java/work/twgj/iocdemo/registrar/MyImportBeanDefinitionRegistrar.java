package work.twgj.iocdemo.registrar;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import work.twgj.iocdemo.bean.Dog;

/**
 * @author weijie.zhu
 * @date 2023/11/9 15:31
 */
public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        final String beanName = "dog";
        boolean contains = registry.containsBeanDefinition(beanName);
        if (!contains){
            RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(Dog.class);
            registry.registerBeanDefinition(beanName,rootBeanDefinition);
        }
    }
}
