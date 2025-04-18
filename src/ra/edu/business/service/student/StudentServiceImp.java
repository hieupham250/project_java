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
    public boolean isAccountEmailExist(String email) {
        return studentDao.isAccountEmailExist(email);
    }

    @Override
    public boolean create(Student student) {
        return studentDao.create(student);
    }

    @Override
    public boolean update(Student student) {
        return false;
    }

    @Override
    public boolean delete(Student student) {
        return false;
    }
}
