package work.twgj.testingdemo;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class TestingDemoApplicationTests {


    /**
     * 所有测试开始前执行
     * 修饰的方法必须是static
     * */
    @BeforeAll
    public static void BeforeAll(){
        log.info("BeforeAll");
    }

    /**
     * 每个测试开始前执行
     * */
    @BeforeEach
    public void BeforeEach(){
        log.info("BeforeEach");
    }

    /**
     * 测试方法1
     * */
    @Test
    @DisplayName("test 1")
    public void test1(){
        System.out.println("test 1+1=2");
        Assert.assertEquals(2, 1 + 1);
    }

    /**
     * 测试方法2
     * */
    @Test
    @DisplayName("test 2")
    public void test2(){
        System.out.println("test 2+2=4");
        Assert.assertEquals(4, 2 + 2);
    }

    /**
     * 每个测试结束后执行
     * */
    @AfterEach
    public void AfterEach(){
        System.out.println("AfterEach");
    }

    /**
     * 所有测试结束后执行
     * 修饰的方法必须是static
     * */
    @AfterAll
    public static void AfterAll(){
        System.out.println("AfterAll");
    }
}
