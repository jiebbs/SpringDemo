package work.twgj.shirodemo.service;

import work.twgj.shirodemo.entity.UserEntity;

/**
 * @author weijie.zhu
 * @date 2023/11/21 16:52
 */
public interface UserService {

    UserEntity getUserByUserName(String username);
}
