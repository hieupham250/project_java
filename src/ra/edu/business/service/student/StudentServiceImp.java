package ra.edu.business.service.student;

import ra.edu.business.dao.student.StudentDao;
import ra.edu.business.dao.student.StudentDaoImp;
import ra.edu.business.model.Student;

import java.util.List;

public class StudentServiceImp implements StudentService {
    private StudentDao studentDao;

    public StudentServiceImp() {
        studentDao = new StudentDaoImp();
    }

    @Override
    public List<Student> findAll() {
        return studentDao.findAll();
    }

    @Override
    public List<Student> getStudentsByPage(int page, int pageSize) {
        return studentDao.getStudentsByPage(page, pageSize);
    }

    @Override
    public Student getStudentById(int id) {
        return studentDao.getStudentById(id);
    }

    @Override
    public List<Student> searchStudents(String name, int page, int pageSize, int[] totalRecordsOut) {
        return studentDao.searchStudents(name, page, pageSize, totalRecordsOut);
    }

    @Override
    public List<Student> getStudentsSorted(String sortOption, int page, int pageSize) {
        return studentDao.getStudentsSorted(sortOption, page, pageSize);
    }

    @Override
    public boolean isAccountEmailExist(String email) {
        return studentDao.isAccountEmailExist(email);
    }

    @Override
    public boolean checkStudentHasCourses(int id) {
        return studentDao.checkStudentHasCourses(id);
    }

    @Override
    public boolean create(Student student) {
        return studentDao.create(student);
    }

    @Override
    public boolean update(Student student) {
        return studentDao.update(student);
    }

    @Override
    public boolean delete(Student student) {
        return studentDao.delete(student);
    }

    @Override
    public boolean registerCourse(int studentId, int courseId) {
        return studentDao.registerCourse(studentId, courseId);
    }
}
