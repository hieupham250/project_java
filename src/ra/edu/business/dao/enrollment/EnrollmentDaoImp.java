package ra.edu.business.dao.enrollment;

import ra.edu.business.config.ConnectionDB;
import ra.edu.business.model.RegisteredStudentInfo;
import ra.edu.datatype.StatusEnrollment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentDaoImp implements EnrollmentDao {
    @Override
    public List<RegisteredStudentInfo> getRegisteredStudentsByCourse(int courseId, int page, int pageSize, int[] totalRecordsOut) {
        List<RegisteredStudentInfo> studentsEnrolledInCourses  = new ArrayList<>();
        Connection conn = null;
        CallableStatement cstmt = null;
        try {
            conn = ConnectionDB.openConnection();
            int offset = (page - 1) * pageSize;
            cstmt = conn.prepareCall("{call get_registered_students_by_course(?, ?, ?, ?)}");
            cstmt.setInt(1, courseId);
            cstmt.setInt(2, pageSize);
            cstmt.setInt(3, offset);
            cstmt.registerOutParameter(4, Types.INTEGER);
            boolean hasResultSet = cstmt.execute();
            if(hasResultSet) {
                ResultSet rs = cstmt.getResultSet();
                while (rs.next()) {
                    RegisteredStudentInfo registeredStudentInfo = new RegisteredStudentInfo(
                            rs.getInt("studentId"),
                            rs.getString("nameStudent"),
                            rs.getString("email"),
                            rs.getString("phone"),
                            rs.getInt("courseId"),
                            rs.getString("nameCourse"),
                            rs.getTimestamp("registered_at"),
                            StatusEnrollment.valueOf(rs.getString("status"))
                    );
                    studentsEnrolledInCourses.add(registeredStudentInfo);
                }
            }
            totalRecordsOut[0] = cstmt.getInt(4);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, cstmt);
        }
        return studentsEnrolledInCourses;
    }

    @Override
    public boolean approveOrDenyRegistration(int studentId, int courseId, String newStatus) {
        Connection conn = null;
        CallableStatement cstmt = null;
        try {
            conn = ConnectionDB.openConnection();
            cstmt = conn.prepareCall("{call approve_or_deny_registration(?, ?, ?)}");
            cstmt.setInt(1, studentId);
            cstmt.setInt(2, courseId);
            cstmt.setString(3, newStatus);
            int result = cstmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, cstmt);
        }
        return false;
    }

    @Override
    public boolean deleteEnrollment(int studentId, int courseId) {
        Connection conn = null;
        CallableStatement cstmt = null;
        try {
            conn = ConnectionDB.openConnection();
            cstmt = conn.prepareCall("{call delete_enrollment(?, ?)}");
            cstmt.setInt(1, studentId);
            cstmt.setInt(2, courseId);
            int result = cstmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, cstmt);
        }
        return false;
    }
}
