package ra.edu.business.service.student;

import ra.edu.business.model.RegisteredCourseInfo;
import ra.edu.business.model.Student;
import ra.edu.business.service.AppService;

import java.util.List;

public interface StudentService extends AppService<Student> {
    @Override
    public List<Student> findAll();

    List<Student> getStudentsByPage(int page, int pageSize);

    Student getStudentById(int id);

    List<Student> searchStudents(String name, int page, int pageSize, int[] totalRecordsOut);

    List<Student> getStudentsSorted(String sortOption, int page, int pageSize);

    boolean isAccountEmailExist(String email);

    @Override
    public boolean create(Student student);

    @Override
    public boolean update(Student student);

    @Override
    public boolean delete(Student student);

    List<RegisteredCourseInfo> getMyRegisteredCourses(int id, int page, int pageSize, int[] totalRecordsOut);

    boolean registerCourse(int studentId, int courseId);

    public boolean cancelCourseRegistration(int studentId, int courseId);

    boolean updatePassword(int studentId, String newPassword);
}
