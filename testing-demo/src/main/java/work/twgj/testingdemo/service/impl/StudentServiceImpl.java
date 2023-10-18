package work.twgj.testingdemo.service.impl;

import org.springframework.stereotype.Service;
import work.twgj.testingdemo.entity.StudentEntity;
import work.twgj.testingdemo.mapper.StudentMapper;
import work.twgj.testingdemo.service.StudentService;

import javax.annotation.Resource;

/**
 * @author weijie.zhu
 * @date 2023/9/28 16:33
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Resource
    private StudentMapper studentMapper;

    @Override
    public int add(StudentEntity entity) {
        return studentMapper.insert(entity);
    }

    @Override
    public int update(StudentEntity entity) {
        return studentMapper.updateById(entity);
    }

    @Override
    public int delete(String sno) {
        return studentMapper.deleteById(sno);
    }

    @Override
    public StudentEntity getStudentBySno(String sno) {
        return studentMapper.selectById(sno);
    }
}
