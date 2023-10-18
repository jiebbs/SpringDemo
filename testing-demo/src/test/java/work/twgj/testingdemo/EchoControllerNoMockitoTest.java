package work.twgj.testingdemo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.StandardCharsets;

/**
 * 当项目中有使用druid,并开启了web-stat-filter时SpringBootTest中必须要设置webEnvironment为WebEnvironment.RANDOM_PORT或
 * WebEnvironment.DEFINED_PORT，默认是WebEnvironment.MOCK，这个设置默认是不会对Filter、Servlet进行初始化的，所以会报空指针。
 * @author weijie.zhu
 * @date 2023/10/18 11:57
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
public class EchoControllerNoMockitoTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void echo() throws Exception {
        final String result = mockMvc.perform(
                MockMvcRequestBuilders.get("/echo/")
                .param("name","twgj")
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse()
                .getContentAsString(StandardCharsets.UTF_8);
        Assertions.assertEquals("Hello,twgj",result);
    }
}
