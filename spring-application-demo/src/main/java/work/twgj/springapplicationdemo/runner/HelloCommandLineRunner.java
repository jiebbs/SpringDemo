package work.twgj.springapplicationdemo.runner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author weijie.zhu
 * @date 2023/11/16 11:45
 */
@Component
@Slf4j
public class HelloCommandLineRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        log.info("HelloCommandRunner: hello spring boot");
    }
}
