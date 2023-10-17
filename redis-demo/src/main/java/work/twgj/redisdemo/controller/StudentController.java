package work.twgj.redisdemo.controller;

import org.springframework.web.bind.annotation.*;
import work.twgj.redisdemo.common.ApiResponse;
import work.twgj.redisdemo.entity.StudentEntity;
import work.twgj.redisdemo.service.StudentService;


import javax.annotation.Resource;

/**
 * @author weijie.zhu
 * @date 2023/9/27 16:44
 */
@RestController
@RequestMapping("/student")
public class StudentController {

    @Resource
    private StudentService studentService;

    @PostMapping("/add")
    public ApiResponse add(@RequestBody StudentEntity entity){
        studentService.add(entity);
        return ApiResponse.success();
    }

    @PostMapping("/update")
    public ApiResponse<StudentEntity> update(@RequestBody StudentEntity entity){
        return ApiResponse.success(studentService.update(entity));
    }

    @PostMapping("/delete/{sno}")
    public ApiResponse delete(@PathVariable("sno")String sno){
        studentService.delete(sno);
        return ApiResponse.success();
    }

    @GetMapping("/detail/{sno}")
    public ApiResponse<StudentEntity> detail(@PathVariable("sno")String sno){
        return ApiResponse.success(studentService.queryById(sno));
    }
}
