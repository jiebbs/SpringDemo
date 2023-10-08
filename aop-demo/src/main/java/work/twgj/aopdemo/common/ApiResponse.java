package work.twgj.aopdemo.common;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author weijie.zhu
 * @date 2023/9/4 16:25
 */
@Data
@AllArgsConstructor
public class ApiResponse<T> {

    public static final int SUCCESS_CODE = 200;

    public static final int ERROR_CODE = 400;

    public static final int PARAM_ERROR_CODE = 402;

    public static final int EXCEPTION_CODE = 500;

    private int code;
    private String message;
    private T data;

    public static <T> ApiResponse<T> success() {
        return new ApiResponse<T>(SUCCESS_CODE,"操作成功",null);
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<T>(SUCCESS_CODE,"操作成功",data);
    }

    public static <T> ApiResponse<T> error(int code,String message) {
        return new ApiResponse<T>(code, message, null );
    }
}
