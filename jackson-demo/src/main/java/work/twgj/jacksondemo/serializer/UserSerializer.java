package work.twgj.jacksondemo.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import work.twgj.jacksondemo.pojo.User;

import java.io.IOException;

/**
 * @author weijie.zhu
 * @date 2023/10/17 17:11
 */
public class UserSerializer extends JsonSerializer<User> {

    @Override
    public void serialize(User user, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("user-name", user.getUserName());
        jsonGenerator.writeEndObject();
    }
}
