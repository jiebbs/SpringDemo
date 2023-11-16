package work.twgj.iocdemo.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

/**
 * @author weijie.zhu
 * @date 2023/11/9 9:29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
//@Component // 使用注解将bean注入到ioc中
public class User {

    private String name;

    private String age;
}
