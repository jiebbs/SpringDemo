package work.twgj.shirodemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.sql.Timestamp;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 *  要使用cookies做rememberMe功能必须要实现Serializable
 * </p>
 *
 * @author weijie.zhu
 * @since 2023-11-21
 */
@Getter
@Setter
@TableName("t_user")
public class UserEntity implements Serializable {

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 创建时间
     */
    private Timestamp createTime;

    /**
     * 有效状态（0-无效，1-有效）
     */
    private byte status;
}
