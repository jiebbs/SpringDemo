package work.twgj.hibernatevalidatordemo.domain;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * @author weijie.zhu
 * @date 2023/11/16 14:39
 */
@Data
@ToString
public class User {

    @NotBlank(message = "{required}")
    private String name;

    @Email(message = "{invalid}")
    private String email;
}
