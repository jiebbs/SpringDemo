package work.twgj.shirodemo.bean;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;
import work.twgj.shirodemo.common.Constants;
import work.twgj.shirodemo.entity.PermissionEntity;
import work.twgj.shirodemo.entity.RoleEntity;
import work.twgj.shirodemo.entity.UserEntity;
import work.twgj.shirodemo.service.PermissionService;
import work.twgj.shirodemo.service.RoleService;
import work.twgj.shirodemo.service.UserService;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author weijie.zhu
 * @date 2023/11/21 16:17
 */
@Slf4j
public class ShiroRealm extends AuthorizingRealm {

    @Resource
    private UserService userService;

    @Resource
    private RoleService roleService;

    @Resource
    private PermissionService permissionService;

    /**
     * 获取用户角色和权限
     * */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        UserEntity user = (UserEntity) SecurityUtils.getSubject().getPrincipal();
        String username = user.getUsername();

        log.info("用户：{} 获取权限---ShiroRealm.doGetAuthorizationInfo",username);
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

        // 获取所有用户角色
        List<RoleEntity> roleEntityList = roleService.findByUsername(username);
        Set<String> roleSet = new HashSet<>();
        roleEntityList.stream().forEach(r->roleSet.add(r.getName()));
        authorizationInfo.setRoles(roleSet);

        // 获取用户所有权限
        List<PermissionEntity> permissionEntityList = permissionService.findByUsername(username);
        Set<String> permissionSet = new HashSet<>();
        permissionEntityList.stream().forEach(p->permissionSet.add(p.getName()));
        authorizationInfo.setStringPermissions(permissionSet);

        return authorizationInfo;
    }

    /**
     * 登录认证
     * */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();
        String password = new String((char[])authenticationToken.getCredentials());

        log.info("用户：{} 认证---shiroRealm.doGetAuthenticationInfo",username);

        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)){
            throw  new UnknownAccountException("用户名或密码不能为空！");
        }

        UserEntity userEntity = userService.getUserByUserName(username);

        if (userEntity == null){
            throw  new UnknownAccountException("用户名或密码错误！");
        }else if (!StringUtils.equals(userEntity.getPassword(),password)){
            throw  new IncorrectCredentialsException("用户名或密码错误！");
        }else if (Constants.USER_STATUS_DISABLED == userEntity.getStatus()){
            throw new LockedAccountException("账号已被冻结，请联系管理员！");
        }

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(userEntity,password,getName());
        return info;
    }
}
