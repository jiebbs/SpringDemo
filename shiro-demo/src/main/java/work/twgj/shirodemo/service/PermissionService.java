package work.twgj.shirodemo.service;

import work.twgj.shirodemo.entity.PermissionEntity;

import java.util.List;

/**
 * @author weijie.zhu
 * @date 2023/11/22 11:41
 */
public interface PermissionService {

    List<PermissionEntity> findByUsername(String username);
}
