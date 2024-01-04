package work.twgj.springsecuritydemo.config.login;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import work.twgj.springsecuritydemo.bean.LoginUser;
import work.twgj.springsecuritydemo.entity.UserEntity;
import work.twgj.springsecuritydemo.service.UserService;

import javax.annotation.Resource;

/**
 * 自定义用户校验
 * @author weijie.zhu
 * @date 2023/11/27 11:40
 */
@Configuration
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserService userService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userService.findUserByUsername(username);
        // 用户查询不到
        if (userEntity == null){
            throw new UsernameNotFoundException("该用户名未注册");
        }
        // 组装一个模拟数据
        LoginUser loginUser = new LoginUser(userEntity);
        return new User(
                username,
                loginUser.getPassword(),
                loginUser.isEnabled(),
                loginUser.isAccountNonExpired(),
                loginUser.isCredentialsNonExpired(),
                loginUser.isAccountNonLocked(),
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin")
        );
    }
}
