package work.twgj.exceptiondemo.exception;

import lombok.Data;

/**
 * @author weijie.zhu
 * @date 2023/11/3 11:56
 */
@Data
public class UserNotExistException extends RuntimeException {

    private String id;

    public UserNotExistException(String id) {
        super("user not exist");
        this.id = id;
    }
}
