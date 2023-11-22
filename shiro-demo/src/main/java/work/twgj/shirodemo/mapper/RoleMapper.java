package work.twgj.shirodemo.mapper;

import org.apache.ibatis.annotations.Param;
import work.twgj.shirodemo.entity.RoleEntity;
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
public interface RoleMapper extends BaseMapper<RoleEntity> {

    /**
     * 根据用户名查找用户角色
     * @param username
     * @return
     */
    List<RoleEntity> findByUsername(@Param("username")String username);
}
