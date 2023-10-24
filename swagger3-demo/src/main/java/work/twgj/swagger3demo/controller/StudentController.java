package work.twgj.swagger3demo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.oas.annotations.EnableOpenApi;
import work.twgj.swagger3demo.common.ApiResponse;
import work.twgj.swagger3demo.entity.StudentEntity;
import work.twgj.swagger3demo.service.StudentService;

import javax.annotation.Resource;

/**
 * @author weijie.zhu
 * @date 2023/9/28 16:33
 */
@Api(produces = "学生相关API")
@RestController
@RequestMapping("/student")
public class StudentController {

    private static final Logger log = LoggerFactory.getLogger(StudentController.class);

    @Resource
    private StudentService studentService;

    @ApiOperation(value = "新增学生信息")
    @PostMapping("/add")
    @ApiImplicitParam(name = "student",value = "学生实体",required = true,dataType = "StudentEntity")
    public ApiResponse add(@RequestBody StudentEntity student){
        log.info("新增学生信息：{}",student);
        studentService.add(student);
        return ApiResponse.success();
    }

    @ApiOperation(value = "更新学生信息")
    @PostMapping("/update")
    @ApiImplicitParam(name = "student",value = "学生实体",required = true,dataType = "StudentEntity")
    public ApiResponse update(@RequestBody StudentEntity student){
        log.info("更新学生信息：{}",student);
        studentService.update(student);
        return ApiResponse.success();
    }

    @ApiOperation(value = "删除学生信息")
    @PostMapping("/delete/{sno}")
    @ApiImplicitParam(name = "sno",value = "学号",required = true,dataType = "String", paramType = "path")
    public ApiResponse delete(@PathVariable(value = "sno") String sno){
        log.info("删除学生信息,学号：{}",sno);
        studentService.delete(sno);
        return ApiResponse.success();
    }

    @ApiOperation(value = "查询学生详情信息")
    @GetMapping("/detail/{sno}")
    @ApiImplicitParam(name = "sno",value = "学号",required = true,dataType = "String", paramType = "path")
    public ApiResponse<StudentEntity> detail(@PathVariable(value = "sno")String sno){
        StudentEntity entity = studentService.getStudentBySno(sno);
        return ApiResponse.success(entity);
    }
}
