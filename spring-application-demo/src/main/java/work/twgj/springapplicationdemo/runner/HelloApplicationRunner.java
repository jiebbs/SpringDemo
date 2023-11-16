package work.twgj.springapplicationdemo.runner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author weijie.zhu
 * @date 2023/11/16 11:43
 */
@Component
@Slf4j
public class HelloApplicationRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
      log.info("HelloApplicationRunner: hello spring boot");
    }
}
