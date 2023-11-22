package work.twgj.shirodemo.service;

import work.twgj.shirodemo.entity.RoleEntity;

import java.util.List;

/**
 * @author weijie.zhu
 * @date 2023/11/22 11:42
 */
public interface RoleService {

    List<RoleEntity> findByUsername(String username);
}
