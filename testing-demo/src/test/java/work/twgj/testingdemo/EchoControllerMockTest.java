package work.twgj.testingdemo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import work.twgj.testingdemo.service.EchoService;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;

/**
 当项目中有使用druid,并开启了web-stat-filter时SpringBootTest中必须要设置webEnvironment为WebEnvironment.RANDOM_PORT或
 * WebEnvironment.DEFINED_PORT，默认是WebEnvironment.MOCK，这个设置默认是不会对Filter、Servlet进行初始化的，所以会报空指针。
 *
 * @author weijie.zhu
 * @date 2023/10/18 14:10
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
public class EchoControllerMockTest {

    @Resource
    private MockMvc mockMvc;

    @MockBean
    private EchoService echoService;

    @BeforeEach
    void setUp(){
        // 设置固定返回的内容，再echo方法调用对应接口时，不管传入什么参数，都会返回该设置的返回结果
        Mockito.when(echoService.echo(Mockito.any()))
                .thenReturn("twgj说:" + System.currentTimeMillis());
    }

    /**
     * 其他请求方式的写法：
     * 模拟发送一个message参数，值为hello
     * mockMvc.perform(MockMvcRequestBuilders.get("/hello").param("message", "hello"));
     *
     * 模拟提交一个checkbox值，name为hobby，值为sleep和eat
     * mockMvc.perform(MockMvcRequestBuilders.get("/saveHobby").param("hobby", "sleep", "eat"));
     *
     * 使用MultiValueMap构建参数
     * MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
     * params.add("name", "mrbird");
     * params.add("hobby", "sleep");
     * params.add("hobby", "eat");
     * mockMvc.perform(MockMvcRequestBuilders.get("/hobby/save").params(params));
     *
     * 模拟发送JSON参数：
     * String jsonStr = "{\"username\":\"Dopa\",\"passwd\":\"ac3af72d9f95161a502fd326865c2f15\",\"status\":\"1\"}";
     * mockMvc.perform(MockMvcRequestBuilders.post("/user/save").content(jsonStr.getBytes()));
     *
     * 使用jackson 序列号对象
     * User user = new User();
     * user.setUsername("Dopa");
     * user.setPasswd("ac3af72d9f95161a502fd326865c2f15");
     * user.setStatus("1");
     *
     * String userJson = mapper.writeValueAsString(user);
     * mockMvc.perform(MockMvcRequestBuilders.post("/user/save").content(userJson.getBytes()));
     *
     * 模拟Session和Cookie：
     *
     * mockMvc.perform(MockMvcRequestBuilders.get("/index").sessionAttr(name, value));
     * mockMvc.perform(MockMvcRequestBuilders.get("/index").cookie(new Cookie(name, value)));
     *
     * 设置请求的Content-Type：
     *
     * mockMvc.perform(MockMvcRequestBuilders.get("/index").contentType(MediaType.APPLICATION_JSON_UTF8));
     * 设置返回格式为JSON：
     *
     * mockMvc.perform(MockMvcRequestBuilders.get("/user/{id}", 1).accept(MediaType.APPLICATION_JSON));
     * 模拟HTTP请求头：
     *
     * mockMvc.perform(MockMvcRequestBuilders.get("/user/{id}", 1).header(name, values));
     * */

    @Test
    void echo() throws Exception {
        final String result = mockMvc.perform(
                MockMvcRequestBuilders.get("/echo/")
                .param("name","twgj的小屋")
        )
                .andExpect(MockMvcResultMatchers.status().isOk()) // 期待请求返回的响应状态
                .andDo(MockMvcResultHandlers.print()) // 打印日志
                .andReturn() // 获取返回结果
                .getResponse() // 获取响应体
                .getContentAsString(StandardCharsets.UTF_8); // 转换body中的内容

        // 当返回结果符合时，不会做处理，反正会报错
        Assertions.assertTrue(result.startsWith("twgj"));
    }


}
