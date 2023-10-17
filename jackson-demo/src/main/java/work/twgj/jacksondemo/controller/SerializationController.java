package work.twgj.jacksondemo.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import work.twgj.jacksondemo.pojo.User;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @author weijie.zhu
 * @date 2023/10/17 15:55
 */
@RestController
@RequestMapping
public class SerializationController {

    @Resource
    private ObjectMapper mapper;

    @GetMapping("/getUser")
    @JsonView(User.UserNameView.class)
    public User getUser(){
        User user = new User();
        user.setUserName("twgj");
        user.setBirthday(new Date());
        return user;
    }

    /**
     * 手动序列化
     * */
    @GetMapping("/serialization")
    public String serialization(){
        try {
            User user = new User();
            user.setUserName("twgj");
            user.setBirthday(new Date());
            String str = mapper.writeValueAsString(user);
            return str;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 树遍历
     * readTree方法可以接受一个字符串或者字节数组、文件、InputStream等，
     * 返回JsonNode作为根节点，你可以像操作XML DOM那样操作遍历JsonNode以获取数据
     * 反序列化
     * */
    @GetMapping("/readjsonstring")
    public String readJsonString() {
        try {
            String json = "{\"name\":\"twgj\",\"age\":26}";
            JsonNode node = this.mapper.readTree(json);
            String name = node.get("name").asText();
            int age = node.get("age").asInt();
            return name + " " + age;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 绑定对象
     * 当对象json中存在的属性，pojo中不存在时，这种绑定对象的方式会报错
     * 如果设置了反序列化器，将会按照反序列化器对json,进行反序列化
     * */
    @GetMapping("/readjsonasobject")
    public String readjsonasobject(){
        try {
            String json = "{\"user-name\":\"twgj\"}";
            User user = mapper.readValue(json, User.class);
            String name = user.getUserName();
            int age = user.getAge();
            return name + " " + age;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 转换List
     * */
    @GetMapping("/customize")
    public String customize() throws JsonParseException, JsonMappingException, IOException {
        String jsonStr = "[{\"userName\":\"mrbird\",\"age\":26},{\"userName\":\"scott\",\"age\":27}]";
        JavaType type = mapper.getTypeFactory().constructParametricType(List.class, User.class);
        List<User> list = mapper.readValue(jsonStr, type);
        String msg = "";
        for (User user : list) {
            msg += user.getUserName();
        }
        return msg;
    }
}
