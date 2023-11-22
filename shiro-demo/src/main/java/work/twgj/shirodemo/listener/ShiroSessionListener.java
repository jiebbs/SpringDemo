package work.twgj.shirodemo.listener;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author weijie.zhu
 * @date 2023/11/22 16:55
 */
public class ShiroSessionListener implements SessionListener {

    /**
     * 连接统计
     * */
    private static final AtomicInteger sessionCount = new AtomicInteger();


    @Override
    public void onStart(Session session) {
        sessionCount.incrementAndGet();
    }

    @Override
    public void onStop(Session session) {
        sessionCount.decrementAndGet();
    }

    @Override
    public void onExpiration(Session session) {
        sessionCount.decrementAndGet();
    }
}
