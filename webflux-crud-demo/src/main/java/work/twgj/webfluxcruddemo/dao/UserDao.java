package work.twgj.webfluxcruddemo.dao;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import work.twgj.webfluxcruddemo.domain.User;

/**
 * 和 Spring Boot整合Mongo DB 不同的是，我们继承的是ReactiveMongoRepository而非MongoRepository，
 * 它所提供的方法都是响应式的
 * @author weijie.zhu
 * @date 2023/11/21 9:18
 */
@Repository
public interface UserDao extends ReactiveMongoRepository<User,String> {
}
