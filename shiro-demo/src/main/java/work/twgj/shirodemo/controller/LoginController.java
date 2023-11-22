package work.twgj.shirodemo.controller;

import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.crypto.digest.MD5;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import work.twgj.shirodemo.common.ApiResponse;
import work.twgj.shirodemo.entity.UserEntity;

import java.io.UnsupportedEncodingException;


/**
 * @author weijie.zhu
 * @date 2023/11/21 16:55
 */
@Controller
@Slf4j
public class LoginController {

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/login")
    @ResponseBody
    public ApiResponse login(String username,String password,Boolean rememberMe){
        log.info("用户{}登录...",username);
        // 密码进行MD5加密
        String md5Password = DigestUtil.md5Hex(password);
        UsernamePasswordToken token = new UsernamePasswordToken(username,md5Password,rememberMe);
        // 获取subject
        Subject subject = SecurityUtils.getSubject();
        try{
            subject.login(token);
            return ApiResponse.success();
        }catch (UnknownAccountException e){
            log.error(e.getMessage());
            return ApiResponse.unauthorized(e.getMessage());
        }catch (IncorrectCredentialsException e){
            log.error(e.getMessage());
            return ApiResponse.unauthorized(e.getMessage());
        }catch (LockedAccountException e){
            log.error(e.getMessage());
            return ApiResponse.unauthorized(e.getMessage());
        }catch (AuthenticationException e){
            log.error(e.getMessage());
            return ApiResponse.unauthorized("认证失败");
        }
    }

    @GetMapping("/")
    public String redirectIndex(){
        return "redirect:/index";
    }

    @GetMapping("/index")
    public String index(Model model){
        // 登录成功后通过subject获取用户信息
        UserEntity userEntity = (UserEntity) SecurityUtils.getSubject().getPrincipal();
        model.addAttribute("user",userEntity);
        return "index";
    }

    @GetMapping("/403")
    public String forbid(){
        return "403";
    }
}
