package work.twgj.testingdemo.service.impl;

import org.springframework.stereotype.Service;
import work.twgj.testingdemo.service.EchoService;

/**
 * @author weijie.zhu
 * @date 2023/10/18 10:51
 */
@Service
public class EchoServiceImpl implements EchoService {

    @Override
    public String echo(String foo) {
        return "Hello," + foo;
    }
}
