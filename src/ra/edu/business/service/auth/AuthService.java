package ra.edu.business.service.auth;

import ra.edu.business.model.Admin;
import ra.edu.business.model.Student;

public interface AuthService {
    Admin loginAdmin(String username, String password);
    Student loginStudent(String email, String password);
    void logout();
}
