package work.twgj.iocdemo.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import work.twgj.iocdemo.service.CalculateService;

import java.util.Arrays;

/**
 * @author weijie.zhu
 * @date 2023/11/9 14:48
 */
@Service
@Profile("java8")
@Slf4j
public class Java8CalculateServiceImpl implements CalculateService {

    @Override
    public int sum(Integer... value) {
        log.info("java8下运行...");
        return Arrays.stream(value).reduce(0,Integer::sum);
    }
}
