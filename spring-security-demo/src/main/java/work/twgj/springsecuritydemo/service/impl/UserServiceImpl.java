package work.twgj.springsecuritydemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.stereotype.Service;
import work.twgj.springsecuritydemo.entity.UserEntity;
import work.twgj.springsecuritydemo.mapper.UserMapper;
import work.twgj.springsecuritydemo.service.UserService;

import javax.annotation.Resource;

/**
 * @author weijie.zhu
 * @date 2023/11/27 11:44
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public UserEntity findUserByUsername(String username) {
        LambdaQueryWrapper<UserEntity> wrapper = new LambdaQueryWrapper(UserEntity.class);
        wrapper.like(UserEntity::getUsername,username);
        return userMapper.selectOne(wrapper);
    }
}
