package ra.edu.business.dao.auth;

import ra.edu.business.config.ConnectionDB;
import ra.edu.business.model.Admin;
import ra.edu.business.model.Student;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthDaoImp implements AuthDao {
    private static Object currentUser = null;

    @Override
    public Admin loginAdmin(String username, String password) {
        Connection conn = null;
        CallableStatement stmt = null;
        Admin admin = null;
        ResultSet rs = null;
        try {
            conn = ConnectionDB.openConnection();
            stmt = conn.prepareCall("{call login_admin(?,?)}");
            stmt.setString(1, username);
            stmt.setString(2, password);
            rs = stmt.executeQuery();
            if (rs.next()) {
                currentUser = new Admin(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, stmt);
        }
        return (Admin) currentUser;
    }

    @Override
    public Student loginStudent(String email, String password) {
        Connection conn = null;
        CallableStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = ConnectionDB.openConnection();
            stmt = conn.prepareCall("{call login_student(?,?)}");
            stmt.setString(1, email);
            stmt.setString(2, password);
            rs = stmt.executeQuery();
            if (rs.next()) {
                currentUser = new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDate("dob").toLocalDate(),
                        rs.getString("email"),
                        rs.getBoolean("sex"),
                        rs.getString("phone"),
                        rs.getString("password"),
                        rs.getDate("create_at").toLocalDate()
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, stmt);
        }
        return (Student) currentUser;
    }

    @Override
    public void logout() {
        if (currentUser != null) {
            currentUser = null;
        }
    }
}
