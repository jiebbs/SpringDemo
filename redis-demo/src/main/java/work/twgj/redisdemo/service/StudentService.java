package work.twgj.redisdemo.service;

import work.twgj.redisdemo.entity.StudentEntity;

/**
 * @author weijie.zhu
 * @date 2023/9/27 16:32
 */
public interface StudentService {

    int add(StudentEntity entity);

    StudentEntity update(StudentEntity entity);

    int delete(String sno);

    StudentEntity queryById(String sno);
}
