package work.twgj.webfluxcruddemo.domain;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author weijie.zhu
 * @date 2023/11/21 9:16
 */
@Document(collection = "user")
@Data
@ToString
public class User {

    @Id
    private String id;

    private String name;

    private Integer age;

    private String description;
}
