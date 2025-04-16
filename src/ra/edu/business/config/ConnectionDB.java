package ra.edu.business.config;

import java.sql.*;

public class ConnectionDB {
    private static String url = "jdbc:mysql://localhost:3306/course_management";
    private static String user = "root";
    private static String password = "12345678";

    public static Connection openConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println("\u001B[31m Lỗi kết nối CSDL do: " + e.getMessage() + "\u001B[0m");
        } catch (Exception e) {
            System.out.println("\u001B[31m Có lỗi không xác định khi kết nối CSDL: " + e.getMessage() + "\u001B[0m");
        }
        return conn;
    }

    public static void closeConnection(Connection conn, CallableStatement stmt) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
