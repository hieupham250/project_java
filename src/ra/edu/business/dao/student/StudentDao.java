package ra.edu.business.dao.student;

import ra.edu.business.dao.AppDao;
import ra.edu.business.model.Student;

import java.util.List;

public interface StudentDao extends AppDao<Student> {
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
