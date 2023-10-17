package work.twgj.redisdemo.service.impl;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import work.twgj.redisdemo.entity.StudentEntity;
import work.twgj.redisdemo.mapper.StudentMapper;
import work.twgj.redisdemo.service.StudentService;

import javax.annotation.Resource;

/**
 * @author weijie.zhu
 * @date 2023/9/27 16:32
 */
@Service
@CacheConfig(cacheNames = "student")
public class StudentServiceImpl implements StudentService {

    @Resource
    private StudentMapper studentMapper;

    @Override
    public int add(StudentEntity entity){
        return studentMapper.insert(entity);
    }

    @Override
    @CachePut(key = "#p0.sno")
    public StudentEntity update(StudentEntity entity){
        studentMapper.updateById(entity);
        return studentMapper.selectById(entity.getSno());
    }

    @Override
    @CacheEvict(key = "#p0")
    public int delete(String sno){
        return  studentMapper.deleteById(sno);
    }

    @Override
    @Cacheable(key = "#p0")
    public StudentEntity queryById(String sno){
        return studentMapper.selectById(sno);
    }
}
