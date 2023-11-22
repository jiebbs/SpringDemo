package work.twgj.shirodemo.service.Impl;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DefaultSessionContext;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.springframework.stereotype.Service;
import work.twgj.shirodemo.entity.UserEntity;
import work.twgj.shirodemo.service.SessionService;
import work.twgj.shirodemo.vo.UserOnlineVo;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author weijie.zhu
 * @date 2023/11/22 17:05
 */
@Service
public class SessionServiceImpl implements SessionService {

    @Resource
    private SessionDAO sessionDAO;

    @Override
    public List<UserOnlineVo> list() {
        List<UserOnlineVo> userOnlineVoList = new ArrayList<>();
        // SessionDao的getActiveSessions()方法，我们可以获取所有有效的Session，通过该Session，我们还可以获取到当前用户的Principal信息
        Collection<Session> sessionList = sessionDAO.getActiveSessions();
        sessionList.stream().forEach(session -> {
            UserOnlineVo userOnlineVo = new UserOnlineVo();
            UserEntity userEntity = null;
            SimplePrincipalCollection principalCollection = new SimplePrincipalCollection();
            if (session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY) != null){
                principalCollection = (SimplePrincipalCollection) session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
                userEntity = (UserEntity) principalCollection.getPrimaryPrincipal();
                userOnlineVo.setUserId(userEntity.getId());
                userOnlineVo.setUsername(userEntity.getUsername());
            }
            userOnlineVo.setId((String) session.getId());
            userOnlineVo.setHost(session.getHost());
            userOnlineVo.setStartTimestamp(session.getStartTimestamp());
            userOnlineVo.setLastAccessTime(session.getLastAccessTime());
            Long timeout = session.getTimeout();
            // 当某个用户被踢出后（Session Time置为0），该Session并不会立刻从ActiveSessions中剔除，
            // 所以我们可以通过其timeout信息来判断该用户在线与否
            if (timeout == 0L){
                userOnlineVo.setStatus("离线");
            }else {
                userOnlineVo.setStatus("在线");
            }
            session.setTimeout(timeout);
            userOnlineVoList.add(userOnlineVo);
        });
        return userOnlineVoList;
    }

    /**
     * 通过shiro强行让用户下线
     * redis实现
     * */
    @Override
    public boolean forceLogout(String sessionId) {
        Session session = sessionDAO.readSession(sessionId);
        sessionDAO.delete(session);
        return true;
    }
}
