package work.twgj.mongodemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import work.twgj.mongodemo.common.ApiResponse;
import work.twgj.mongodemo.domain.User;
import work.twgj.mongodemo.service.UserService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author weijie.zhu
 * @date 2023/11/20 9:23
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/getUsers")
    public ApiResponse getUsers(){
        return ApiResponse.success(userService.getUsers());
    }

    @GetMapping("/getUser/{id}")
    public ApiResponse getUser(@PathVariable("id")String id){
        return ApiResponse.success(userService.getUser(id));
    }

    @PostMapping("/createUser")
    public ApiResponse createUser(@RequestBody User user){
        return ApiResponse.success(userService.createUser(user));
    }

    @PostMapping("/updateUser")
    public ApiResponse updateUser(@RequestBody User user){
        userService.updateUser(user);
        return ApiResponse.success();
    }

    @PostMapping("/deleteUser/{id}")
    public ApiResponse deleteUser(@PathVariable("id")String id){
        userService.deleteUser(id);
        return ApiResponse.success();
    }

    @GetMapping("/findByAgeBetween")
    public ApiResponse<List<User>> findByAgeBetween(@RequestParam("beginAge")Integer beginAge,@RequestParam("endAge")Integer endAge){
        return ApiResponse.success(userService.getUsersBetweenAge(beginAge,endAge));
    }

    @GetMapping("/getUserByPage")
    public ApiResponse<Page<User>> getUserByPage(Integer pageNo,Integer pageSize,User user){
        return ApiResponse.success(userService.getUserByPage(pageNo,pageSize,user));
    }
}
