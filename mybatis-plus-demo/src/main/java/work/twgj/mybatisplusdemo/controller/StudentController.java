package work.twgj.mybatisplusdemo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import work.twgj.mybatisplusdemo.common.ApiResponse;
import work.twgj.mybatisplusdemo.entity.StudentEntity;
import work.twgj.mybatisplusdemo.service.StudentService;

import javax.annotation.Resource;

/**
 * @author weijie.zhu
 * @date 2023/9/28 16:33
 */
@RestController
@RequestMapping("/student")
public class StudentController {

    private static final Logger log = LoggerFactory.getLogger(StudentController.class);

    @Resource
    private StudentService studentService;

    @PostMapping("/add")
    public ApiResponse add(@RequestBody StudentEntity entity){
        log.info("新增学生信息：{}",entity);
        studentService.add(entity);
        return ApiResponse.success();
    }

    @PostMapping("/update")
    public ApiResponse update(@RequestBody StudentEntity entity){
        log.info("更新学生信息：{}",entity);
        studentService.update(entity);
        return ApiResponse.success();
    }

    @PostMapping("/delete/{sno}")
    public ApiResponse delete(@PathVariable(value = "sno") String sno){
        log.info("删除学生信息,学号：{}",sno);
        studentService.delete(sno);
        return ApiResponse.success();
    }

    @GetMapping("/detail/{sno}")
    public ApiResponse<StudentEntity> detail(@PathVariable(value = "sno")String sno){
        StudentEntity entity = studentService.getStudentBySno(sno);
        return ApiResponse.success(entity);
    }
}
