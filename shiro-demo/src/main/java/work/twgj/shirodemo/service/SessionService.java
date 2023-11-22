package work.twgj.shirodemo.service;

import work.twgj.shirodemo.vo.UserOnlineVo;

import java.util.List;

/**
 * @author weijie.zhu
 * @date 2023/11/22 17:05
 */
public interface SessionService {

    List<UserOnlineVo> list();

    boolean forceLogout(String sessionId);
}
