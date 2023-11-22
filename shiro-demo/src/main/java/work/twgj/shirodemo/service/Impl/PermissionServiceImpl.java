package work.twgj.shirodemo.service.Impl;

import org.springframework.stereotype.Service;
import work.twgj.shirodemo.entity.PermissionEntity;
import work.twgj.shirodemo.mapper.PermissionMapper;
import work.twgj.shirodemo.service.PermissionService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author weijie.zhu
 * @date 2023/11/22 11:42
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Resource
    private PermissionMapper permissionMapper;


    @Override
    public List<PermissionEntity> findByUsername(String username) {
        return permissionMapper.findByUsername(username);
    }
}
