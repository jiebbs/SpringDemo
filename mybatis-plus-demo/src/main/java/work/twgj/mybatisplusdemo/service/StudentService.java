package work.twgj.mybatisplusdemo.service;

import work.twgj.mybatisplusdemo.entity.StudentEntity;

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
