package work.twgj.kafkademo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author weijie.zhu
 * @date 2023/11/17 17:34
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Message {

    private String from;

    private String message;
}
