package work.twgj.mongodemo.domain;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author weijie.zhu
 * @date 2023/11/20 9:13
 */
@Document
@Data
@ToString
public class User {

    // 这里需要使用 org.springframework.data.annotation.Id，注解，不要用@MongoId
    @Id
    private String id;

    private String name;

    private Integer age;

    private String description;
}
