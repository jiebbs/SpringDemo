package work.twgj.shirodemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author weijie.zhu
 * @since 2023-11-22
 */
@Getter
@Setter
@TableName("t_permission")
public class PermissionEntity implements Serializable {

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * url地址
     */
    private String url;

    /**
     * url地址描述
     */
    private String name;
}
