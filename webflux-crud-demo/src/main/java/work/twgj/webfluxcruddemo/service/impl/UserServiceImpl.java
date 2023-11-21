package work.twgj.webfluxcruddemo.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import work.twgj.webfluxcruddemo.dao.UserDao;
import work.twgj.webfluxcruddemo.domain.User;
import work.twgj.webfluxcruddemo.service.UserService;

import javax.annotation.Resource;


/**
 * @author weijie.zhu
 * @date 2023/11/21 10:24
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    /**
     * webflux情况下需要引入
     * */
    @Resource
    private ReactiveMongoTemplate reactiveMongoTemplate;

    @Override
    public Flux<User> getUsers() {
        return userDao.findAll();
    }

    @Override
    public Mono<User> getUser(String id) {
        return userDao.findById(id);
    }

    @Override
    public Mono<User> createUser(User user) {
        return userDao.save(user);
    }

    @Override
    public Mono<User> updateUser(User user) {
        return userDao.findById(user.getId())
                .flatMap(u->{
                    BeanUtils.copyProperties(user,u);
                    return userDao.save(u);
                });
    }

    @Override
    public Mono<Void> deleteUser(String id) {
        return userDao.findById(id).flatMap(user -> userDao.deleteById(id));
    }

    @Override
    public Flux<User> getUsersByPage(Integer pageNo, Integer pageSize, User user) {
        Query query = getQuery(user);
        Sort sort = Sort.by(Sort.Direction.DESC,"age");
        Pageable pageable = PageRequest.of(pageNo-1,pageSize,sort);
        return reactiveMongoTemplate.find(query.with(pageable),User.class);
    }

    @Override
    public Mono<Long> getUsersCount(User user) {
        Query query = getQuery(user);
        return reactiveMongoTemplate.count(query,User.class);
    }

    private Query getQuery(User user){
        Query query = new Query();
        Criteria criteria = new Criteria();
        if (StringUtils.isNotEmpty(user.getName())){
            criteria.and("name").regex("^.*" + user.getName() + ".*$");
        }
        if (StringUtils.isNotEmpty(user.getDescription())){
            criteria.and("description").regex("^.*" + user.getDescription() + ".*$");
        }
        query.addCriteria(criteria);
        return query;
    }
}
