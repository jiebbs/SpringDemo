package work.twgj.jacksondemo.pojo;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import work.twgj.jacksondemo.serializer.UserDeserializer;
import work.twgj.jacksondemo.serializer.UserSerializer;

import java.io.Serializable;
import java.util.Date;

/**
 * @author weijie.zhu
 * @date 2023/10/17 15:44
 */
@Data
@JsonIgnoreProperties({"age","password"}) // 批量忽略
//@JsonNaming(PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy.class) // 属性命名序列化和反序列化时命名策略，若使用，则返回序列化时注意字段名称，若与命名策略不一致会报错
//@JsonSerialize(using = UserSerializer.class) // 指定序列化器，配置之后优先按照序列化器进行序列化
//@JsonDeserialize(using = UserDeserializer.class) // 指定反序列化器，配置之后优先按照反序列化器进行反序列化
public class User implements Serializable {

    private static final long serialVersionUID = 6222176558369919436L;

    public interface UserNameView{}
    public interface AllUserFieldView extends UserNameView{}

    @JsonView(UserNameView.class) // @JsonView，作用在类或者属性上，用来定义一个序列化组。
    private String userName;
    @JsonView(AllUserFieldView.class)
    private int age;
    /**
     * 忽略属性值
     * */
    @JsonIgnore
    @JsonView(AllUserFieldView.class)
    private String password;
    /**
     * 将属性名称birthday，变更为别名bth
     * 当配置的objectMapper，同时配置JsonFormat，会以JsonFormat设置的格式进行格式化
     * */
    @JsonProperty("bth")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonView(AllUserFieldView.class)
    private Date birthday;
}
