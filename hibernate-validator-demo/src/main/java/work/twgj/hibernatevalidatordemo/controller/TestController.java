package work.twgj.hibernatevalidatordemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import work.twgj.hibernatevalidatordemo.common.ApiResponse;
import work.twgj.hibernatevalidatordemo.domain.User;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * @author weijie.zhu
 * @date 2023/11/16 11:56
 */
@RestController
@RequestMapping()
@Slf4j
@Validated //开启hibernateValidator 注解
public class TestController {

    /**
     * {required},{invalid} 定义在ValidationMessages.properties中
     * 校验不通过会抛出 javax.validation.ConstraintViolationException
     * */
    @GetMapping("/test1")
    public ApiResponse test1(@NotBlank(message = "{required}")String name,
                             @Email(message = "{invalid}")String email){
        log.info("name：{}",name);
        log.info("email：{}",email);
        return ApiResponse.success();
    }

    /**
     * 校验不通过会抛出 javax.validation.BindException
     * */
    @PostMapping("/test2")
    public ApiResponse test2(@Valid User user){
        log.info("user:{}",user);
        return ApiResponse.success();
    }

    /**
     * 校验不通过会抛出 org.springframework.web.bind.MethodArgumentNotValidException
     * */
    @PostMapping("/test3")
    public ApiResponse test3(@RequestBody @Valid User user){
        log.info("user:{}",user);
        return ApiResponse.success();
    }
}
