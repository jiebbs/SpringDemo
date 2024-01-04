package work.twgj.springsecuritydemo.bean;

import lombok.Data;
import work.twgj.springsecuritydemo.entity.UserEntity;

import java.io.Serializable;

/**
 * @author weijie.zhu
 * @date 2023/11/27 11:53
 */
@Data
public class LoginUser implements Serializable {

    private String username;

    private String password;

    private boolean accountNonExpired;

    private boolean accountNonLocked;

    private boolean credentialsNonExpired;

    private boolean enabled;

    public LoginUser(UserEntity userEntity) {
        this.username = userEntity.getUsername();
        this.password = userEntity.getPassword();
        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.credentialsNonExpired = true;
        this.enabled = true;
    }
}
