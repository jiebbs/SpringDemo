package work.twgj.shirodemo.mapper;

import org.apache.ibatis.annotations.Param;
import work.twgj.shirodemo.entity.PermissionEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author weijie.zhu
 * @since 2023-11-22
 */
public interface PermissionMapper extends BaseMapper<PermissionEntity> {

    /**
     * 根据用户名查找用户权限
     * @param username
     * @return
     */
    List<PermissionEntity> findByUsername(@Param("username") String username);
}
