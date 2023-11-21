package work.twgj.mongodemo.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import work.twgj.mongodemo.domain.User;

import java.util.List;

/**
 * 使用类型Spring-data-jpa的方式
 * @author weijie.zhu
 * @date 2023/11/20 9:15
 */
@Repository
public interface UserDao extends MongoRepository<User,String> {

    List<User> findAllByAgeBetween(Integer beginAge,Integer endAge);

    List<User> findAllByAgeBetweenAndNameLikeAndDescriptionLike(Integer beginAge,Integer endAge,String name,String description);
}
