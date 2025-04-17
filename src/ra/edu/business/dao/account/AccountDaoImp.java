package ra.edu.business.dao.account;

import ra.edu.business.config.ConnectionDB;
import ra.edu.business.model.Account;
import ra.edu.datatype.Role;
import ra.edu.datatype.StatusAccount;
import ra.edu.utils.EnumUtils;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDaoImp implements AccountDao {
    private static Account account = null;
    @Override
    public Account login(String email, String password) {
        Connection conn = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        try {
            conn = ConnectionDB.openConnection();
            cstmt = conn.prepareCall("{call login(?,?)}");
            cstmt.setString(1, email);
            cstmt.setString(2, password);
            rs = cstmt.executeQuery();
            if (rs.next()) {
                account = new Account(
                        rs.getInt("id"),
                        rs.getString("email"),
                        rs.getString("password"),
                        EnumUtils.fromString(Role.class, rs.getString("role")),
                        EnumUtils.fromString(StatusAccount.class, rs.getString("status"))
                );

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, cstmt);
        }
        return account;
    }

    @Override
    public void logout() {
        if (account != null) {
            account = null;
        }
    }
}
