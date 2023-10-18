package work.twgj.testingdemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import work.twgj.testingdemo.common.ApiResponse;
import work.twgj.testingdemo.entity.StudentEntity;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 当项目中有使用druid,并开启了web-stat-filter时SpringBootTest中必须要设置webEnvironment为WebEnvironment.RANDOM_PORT或
 * WebEnvironment.DEFINED_PORT，默认是WebEnvironment.MOCK，这个设置默认是不会对Filter、Servlet进行初始化的，所以会报空指针。
 *
 * @author weijie.zhu
 * @date 2023/10/18 15:36
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
@Slf4j
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    private MockHttpSession session;

    @BeforeEach
    void setUp(){
        // 可以在模拟在session中设置信息
        StudentEntity entity = new StudentEntity();
        entity.setSno("005");
        entity.setSsex("F");
        entity.setSname("小J");
        session = new MockHttpSession();
        session.setAttribute("student",entity);
    }

    @Test
    public void getUser() throws Exception {
        final String result = mockMvc.perform(
                MockMvcRequestBuilders.get("/student/detail/{sno}","003")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse()
                .getContentAsString(Charset.defaultCharset());;

        // 设置返回数据编码
        log.info("返回结果：{}",result);
        /**
         *  SpringBoot自带的jackson解析map时会自动解析为LinkHashMap，需要设置一个javaType做转换
         * */
        JavaType type = mapper.getTypeFactory().constructParametricType(ApiResponse.class, StudentEntity.class);
        ApiResponse<StudentEntity> response = mapper.readValue(result, type);
        StudentEntity data = response.getData();
        Assertions.assertEquals("Jane", data.getSname());
    }

    @Test
    public void saveStudent() throws Exception {
        StudentEntity entity = new StudentEntity();
        entity.setSno("005");
        entity.setSsex("F");
        entity.setSname("小J");

        String entityJson = mapper.writeValueAsString(entity);
        final String result = mockMvc.perform(
                MockMvcRequestBuilders.post("/student/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(entityJson.getBytes())
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse()
                .getContentAsString(Charset.defaultCharset());

        ApiResponse response = mapper.readValue(result, ApiResponse.class);
        Assertions.assertEquals(200, response.getCode());
    }

    @Test
    public void getSession(){
        log.info("get session student:{}",session.getAttribute("student"));
    }
}
