package work.twgj.iocdemo.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import work.twgj.iocdemo.service.CalculateService;

/**
 * @author weijie.zhu
 * @date 2023/11/9 14:47
 */
@Service
@Profile("java7")
@Slf4j
public class Java7CalculateServiceImpl implements CalculateService {

    @Override
    public int sum(Integer... value) {
      log.info("java7下运行...");
      Integer sum = 0;
      for(Integer i:value){
          sum += i;
      }
      return sum;
    }
}
