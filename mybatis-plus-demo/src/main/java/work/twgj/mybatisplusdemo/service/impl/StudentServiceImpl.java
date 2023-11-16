package work.twgj.mybatisplusdemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import work.twgj.mybatisplusdemo.entity.StudentEntity;
import work.twgj.mybatisplusdemo.mapper.StudentMapper;
import work.twgj.mybatisplusdemo.service.StudentService;

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

    @Override
    public Page<StudentEntity> pageList(String sno,Integer pageNo, Integer pageSize) {
        Page<StudentEntity> page = new Page<>(pageNo,pageSize);
        // 使用lambda查询的写法
        LambdaQueryWrapper<StudentEntity> wrapper = Wrappers.lambdaQuery(StudentEntity.class);
        if (StringUtils.isNotEmpty(sno)){
            wrapper.like(StudentEntity::getSno,sno);
        }
        return studentMapper.selectPage(page,wrapper);
    }
}
