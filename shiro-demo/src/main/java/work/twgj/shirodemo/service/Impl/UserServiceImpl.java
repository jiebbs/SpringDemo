package work.twgj.shirodemo.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.stereotype.Service;
import work.twgj.shirodemo.entity.UserEntity;
import work.twgj.shirodemo.mapper.UserMapper;
import work.twgj.shirodemo.service.UserService;

import javax.annotation.Resource;

/**
 * @author weijie.zhu
 * @date 2023/11/21 16:53
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public UserEntity getUserByUserName(String username) {
        LambdaQueryWrapper<UserEntity> wrapper = Wrappers.lambdaQuery(UserEntity.class);
        wrapper.eq(UserEntity::getUsername,username);
        UserEntity userEntity = userMapper.selectOne(wrapper);
        return userEntity;
    }
}
