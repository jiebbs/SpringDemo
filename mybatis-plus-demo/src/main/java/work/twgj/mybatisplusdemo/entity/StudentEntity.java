package work.twgj.mybatisplusdemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>
 * 
 * </p>
 *
 * @author weijie.zhu
 * @since 2023-09-28
 */
@Getter
@Setter
@ToString
@TableName("t_student")
public class StudentEntity {

    /**
     * 学号
     */
    @TableId(value = "SNO", type = IdType.ASSIGN_ID)
    private String sno;

    /**
     * 姓名
     */
    private String sname;

    /**
     * 性别
     */
    private String ssex;
}
