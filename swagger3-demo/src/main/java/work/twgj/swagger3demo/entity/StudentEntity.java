package work.twgj.swagger3demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author weijie.zhu
 * @since 2023-10-19
 */
@ApiModel(description = "学生实体")
@Getter
@Setter
@TableName("t_student")
public class StudentEntity {

    /**
     * 学号
     */
    @ApiModelProperty(value = "学号")
    @TableId(value = "SNO", type = IdType.ASSIGN_ID)
    private String sno;

    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名")
    private String sname;

    /**
     * 性别
     */
    @ApiModelProperty(value = "性别")
    private String ssex;
}
