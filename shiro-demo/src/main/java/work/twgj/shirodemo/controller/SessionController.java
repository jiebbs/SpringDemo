package work.twgj.shirodemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import work.twgj.shirodemo.common.ApiResponse;
import work.twgj.shirodemo.service.SessionService;
import work.twgj.shirodemo.vo.UserOnlineVo;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author weijie.zhu
 * @date 2023/11/22 17:22
 */
@Controller
@RequestMapping("/online")
@Slf4j
public class SessionController {

    @Resource
    private SessionService sessionService;

    @GetMapping("/index")
    public String online(){
        return "online";
    }

    @GetMapping("/list")
    @ResponseBody
    public List<UserOnlineVo> list(){
        return sessionService.list();
    }

    @GetMapping("/forceLogout/{id}")
    @ResponseBody
    public ApiResponse forceLogout(@PathVariable("id") String id){
        try{
            // 这里有个bug 如果用户使用rememberMe,则剔除的用户从新刷新页面的情况下，cookies信息会自动让剔除用户自动登录，
            // 这个剔除功能就没用用处了
            sessionService.forceLogout(id);
            return ApiResponse.success();
        }catch (Exception e){
            log.error("强制用户下线异常",e);
            return ApiResponse.error(ApiResponse.EXCEPTION_CODE,"强制用户下线异常");
        }
    }
}
