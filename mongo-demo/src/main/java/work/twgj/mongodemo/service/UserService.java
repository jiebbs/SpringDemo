package work.twgj.mongodemo.service;

import org.springframework.data.domain.Page;
import work.twgj.mongodemo.domain.User;

import java.util.List;

/**
 * @author weijie.zhu
 * @date 2023/11/20 9:17
 */
public interface UserService {

    List<User> getUsers();

    User getUser(String id);

    User createUser(User user);

    void deleteUser(String id);

    void updateUser(User user);

    List<User> getUsersBetweenAge(Integer beginAge,Integer endAge);

    Page<User> getUserByPage(Integer pageNo,Integer pageSize,User user);

}
