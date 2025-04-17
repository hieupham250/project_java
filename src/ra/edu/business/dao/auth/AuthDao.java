package ra.edu.business.dao.auth;

import ra.edu.business.model.Admin;
import ra.edu.business.model.Student;

public interface AuthDao {
    Admin loginAdmin(String username, String password);
    Student loginStudent(String email, String password);
    void logout();
}
