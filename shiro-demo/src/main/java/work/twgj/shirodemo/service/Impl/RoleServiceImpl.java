package work.twgj.shirodemo.service.Impl;

import org.springframework.stereotype.Service;
import work.twgj.shirodemo.entity.RoleEntity;
import work.twgj.shirodemo.mapper.RoleMapper;
import work.twgj.shirodemo.service.RoleService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author weijie.zhu
 * @date 2023/11/22 11:42
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleMapper roleMapper;

    @Override
    public List<RoleEntity> findByUsername(String username) {
        return roleMapper.findByUsername(username);
    }
}
