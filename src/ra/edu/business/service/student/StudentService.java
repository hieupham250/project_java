package ra.edu.business.service.student;

import ra.edu.business.model.Student;
import ra.edu.business.service.AppService;

import java.util.List;

public interface StudentService extends AppService<Student> {
    @Override
    public List<Student> findAll();

    boolean isAccountEmailExist(String email);

    @Override
    public boolean create(Student student);

    @Override
    public boolean update(Student student);

    @Override
    public boolean delete(Student student);
}
