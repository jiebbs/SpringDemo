package work.twgj.mongodemo.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;
import work.twgj.mongodemo.dao.UserDao;
import work.twgj.mongodemo.domain.User;
import work.twgj.mongodemo.service.UserService;

import javax.annotation.Resource;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

/**
 * @author weijie.zhu
 * @date 2023/11/20 9:19
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Resource
    private MongoTemplate mongoTemplate;

    @Override
    public List<User> getUsers() {
        return userDao.findAll();
    }

    @Override
    public User getUser(String id) {
        return userDao.findById(id).orElse(null);
    }

    @Override
    public User createUser(User user) {
        user.setId(null);
        return userDao.save(user);
    }

    @Override
    public void deleteUser(String id) {
        userDao.deleteById(id);
    }

    @Override
    public void updateUser(User user) {
        userDao.findById(user.getId()).ifPresent(
                u -> {
                    u.setAge(user.getAge());
                    u.setName(user.getName());
                    u.setDescription(user.getDescription());
                    userDao.save(u);
                }
        );
    }

    @Override
    public List<User> getUsersBetweenAge(Integer beginAge, Integer endAge) {
        return userDao.findAllByAgeBetween(beginAge,endAge);
    }

    /**
     * 分页需要使用到mongoTemplate
     * mongoTemplate中模糊查询使用正则表达式
     * mongoTemplate中分页是从第0页开始的
     * */
    @Override
    public Page<User> getUserByPage(Integer pageNo, Integer pageSize,User user) {
        Query query = new Query();
        Criteria criteria = new Criteria();
        if (StringUtils.isNotEmpty(user.getName())){
            criteria.and("name").regex("^.*" + user.getName() + ".*$");
        }
        if (StringUtils.isNotEmpty(user.getDescription())){
            criteria.and("description").regex("^.*" + user.getDescription() + ".*$");
        }

        query.addCriteria(criteria);
        Sort sort = Sort.by(Sort.Direction.DESC, "age");
        Pageable pageable = PageRequest.of(pageNo-1,pageSize,sort);

        List<User> users = mongoTemplate.find(query.with(pageable),User.class);
        return PageableExecutionUtils.getPage(users,pageable,()->mongoTemplate.count(query,User.class));
    }
}
