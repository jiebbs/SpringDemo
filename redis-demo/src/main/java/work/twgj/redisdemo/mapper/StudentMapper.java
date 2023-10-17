package work.twgj.redisdemo.mapper;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import work.twgj.redisdemo.entity.StudentEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author weijie.zhu
 * @since 2023-10-17
 */
@CacheConfig(cacheNames = "student")
public interface StudentMapper extends BaseMapper<StudentEntity> {


}
