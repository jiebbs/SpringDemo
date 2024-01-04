package work.twgj.springsecuritydemo.service;

import work.twgj.springsecuritydemo.entity.UserEntity;

/**
 * @author weijie.zhu
 * @date 2023/11/27 11:44
 */
public interface UserService {

    UserEntity findUserByUsername(String username);
}
