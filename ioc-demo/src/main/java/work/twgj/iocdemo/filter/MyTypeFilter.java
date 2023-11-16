package work.twgj.iocdemo.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.util.StringUtils;

import java.io.IOException;

/**
 * @author weijie.zhu
 * @date 2023/11/9 11:58
 */
@Slf4j
public class MyTypeFilter implements TypeFilter {

    /**
     * MetadataReader：当前正在扫描的类的信息；
     * MetadataReaderFactory：可以通过它来获取其他类的信息;
     * */
    @Override
    public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
        // 获取当前扫描的类的注解信息
        AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
        // 获取当前正在扫描的类信息
        ClassMetadata classMetadata = metadataReader.getClassMetadata();
        // 获取当前扫描类的路径信息
        Resource resource = metadataReader.getResource();

        String className = classMetadata.getClassName();
        return StringUtils.hasText("er");
    }
}
