package work.twgj.testingdemo.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import work.twgj.testingdemo.entity.StudentEntity;

import javax.annotation.Resource;

/**
 * @author weijie.zhu
 * @date 2023/10/18 15:19
 */
@SpringBootTest
public class StudentServiceTest {

    @Resource
    private StudentService studentService;

    @Test
    public void getStudent(){
        StudentEntity entity = studentService.getStudentBySno("002");
        Assertions.assertEquals("Mike",entity.getSname(),"用户名为:" + entity.getSname());
    }

    @Test
    @Transactional // 在test方法上加上Transactional方法结束后数据会回滚，不会真正进入到数据表中
    public void addStudent(){
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setSno("005");
        studentEntity.setSname("小帅");
        studentEntity.setSsex("F");
        int i = studentService.add(studentEntity);
        Assertions.assertEquals(1,i);
    }
}
