package work.twgj.iocdemo.importSelector;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author weijie.zhu
 * @date 2023/11/9 15:02
 */
public class MyImportSelector implements ImportSelector {

    /**
     *
     * */
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        return new String[]{
                "work.twgj.iocdemo.bean.Cat"
        };
    }
}
