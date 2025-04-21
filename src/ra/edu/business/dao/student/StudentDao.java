package ra.edu.business.dao.student;

import ra.edu.business.dao.AppDao;
import ra.edu.business.model.Student;

import java.util.List;

public interface StudentDao extends AppDao<Student> {
    @Override
    public List<Student> findAll();

    List<Student> getStudentsByPage(int page, int pageSize);

    Student getStudentById(int id);

    List<Student> searchStudents(String name, int page, int pageSize, int[] totalRecordsOut);

    List<Student> getStudentsSorted(String sortOption, int page, int pageSize);

    boolean isAccountEmailExist(String email);

    boolean checkStudentHasCourses(int id);

    @Override
    public boolean create(Student student);

    @Override
    public boolean update(Student student);

    @Override
    public boolean delete(Student student);
}
