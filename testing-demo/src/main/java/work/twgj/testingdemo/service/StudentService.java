package work.twgj.testingdemo.service;

import work.twgj.testingdemo.entity.StudentEntity;

/**
 * @author weijie.zhu
 * @date 2023/9/28 16:32
 */
public interface StudentService {

    int add(StudentEntity entity);

    int update(StudentEntity entity);

    int delete(String sno);

    StudentEntity getStudentBySno(String sno);
}
