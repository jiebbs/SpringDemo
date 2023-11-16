package work.twgj.autoconfigdemo.selector;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import work.twgj.autoconfigdemo.configuration.HelloConfiguration;

/**
 * @author weijie.zhu
 * @date 2023/11/9 16:03
 */
public class HelloImportSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        return new String[]{
                HelloConfiguration.class.getName()
        };
    }
}
