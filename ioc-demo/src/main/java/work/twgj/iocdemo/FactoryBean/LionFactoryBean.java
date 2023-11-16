package work.twgj.iocdemo.FactoryBean;

import org.springframework.beans.factory.FactoryBean;
import work.twgj.iocdemo.bean.Lion;

/**
 * @author weijie.zhu
 * @date 2023/11/9 15:48
 */
public class LionFactoryBean implements FactoryBean<Lion> {

    @Override
    public Lion getObject() throws Exception {
        return new Lion();
    }

    @Override
    public Class<?> getObjectType() {
        return Lion.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
