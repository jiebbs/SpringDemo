package work.twgj.webfluxcruddemo.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.Repository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import work.twgj.webfluxcruddemo.domain.User;
import work.twgj.webfluxcruddemo.service.UserService;

import javax.annotation.Resource;

/**
 * @author weijie.zhu
 * @date 2023/11/21 10:33
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 以数组的形式一次性返回所有数据
     */
    @GetMapping("/getUsers")
    public Flux<User> getUsers(){
        return userService.getUsers();
    }

    /**
     * 以 Server sent events形式多次返回数据
     */
    @GetMapping(value = "/stream/getUsers",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<User> getUsersStream(){
        return userService.getUsers();
    }

    @PostMapping("/deleteUser/{id}")
    public Mono<ResponseEntity<Void>> deleteUser(@PathVariable(value = "id")String id){
        return userService.deleteUser(id)
                .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/createUser")
    public Mono<User> createUser(@RequestBody User user){
        return userService.createUser(user);
    }

    @PostMapping("/updateUser")
    public Mono<ResponseEntity<User>> updateUser(@RequestBody User user){
        return userService.updateUser(user)
                .map(u-> new ResponseEntity<>(u,HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/getUser/{id}")
    public Mono<ResponseEntity<User>> getUser(@PathVariable("id")String id){
        return userService.getUser(id)
                .map(u->new ResponseEntity<>(u,HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/getUsersByPage")
    public Flux<User> getUsersByPage(Integer pageNo,Integer pageSize,User user){
        return userService.getUsersByPage(pageNo,pageSize,user);
    }

    @GetMapping("/getUsersCount")
    public Mono<Long> getUsersCount(User user){
        return userService.getUsersCount(user);
    }
}
