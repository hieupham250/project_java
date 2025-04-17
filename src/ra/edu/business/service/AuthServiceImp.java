package ra.edu.business.service;

import ra.edu.business.dao.auth.AuthDao;
import ra.edu.business.dao.auth.AuthDaoImp;
import ra.edu.business.model.Admin;
import ra.edu.business.model.Student;

public class AuthServiceImp implements AuthService {
    private final AuthDao authDao;

    public AuthServiceImp() {
        this.authDao = new AuthDaoImp();
    }

    @Override
    public Admin loginAdmin(String username, String password) {
        return authDao.loginAdmin(username, password);
    }

    @Override
    public Student loginStudent(String email, String password) {
        return authDao.loginStudent(email, password);
    }

    @Override
    public void logout() {
        authDao.logout();
    }
}
