package work.twgj.webfluxcruddemo.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import work.twgj.webfluxcruddemo.domain.User;

/**
 * @author weijie.zhu
 * @date 2023/11/21 10:24
 */
public interface UserService {

    Flux<User> getUsers();

    Mono<User> getUser(String id);

    Mono<User> createUser(User user);

    Mono<User> updateUser(User user);

    Mono<Void> deleteUser(String id);

    Flux<User> getUsersByPage(Integer pageNo,Integer pageSize,User user);

    Mono<Long> getUsersCount(User user);
}
