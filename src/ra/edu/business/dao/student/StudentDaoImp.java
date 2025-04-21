package ra.edu.business.dao.student;

import ra.edu.business.config.ConnectionDB;
import ra.edu.business.model.Student;
import ra.edu.business.model.RegisteredCourse;
import ra.edu.datatype.StatusAccount;
import ra.edu.datatype.StatusEnrollment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImp implements StudentDao{
    @Override
    public List<Student> findAll() {
        List<Student> students = new ArrayList<Student>();
        Connection conn = null;
        CallableStatement cstmt = null;
        try {
            conn = ConnectionDB.openConnection();
            cstmt = conn.prepareCall("{call get_students()}");
            ResultSet rs = cstmt.executeQuery();
            while (rs.next()) {
                Student student = new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDate("dob").toLocalDate(),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getBoolean("sex"),
                        rs.getString("phone"),
                        StatusAccount.valueOf(rs.getString("status").toUpperCase()),
                        rs.getInt("account_id"),
                        rs.getDate("create_at").toLocalDate()
                );
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, cstmt);
        }
        return students;
    }

    @Override
    public List<Student> getStudentsByPage(int page, int pageSize) {
        List<Student> students = new ArrayList<Student>();
        Connection conn = null;
        CallableStatement cstmt = null;
        try {
            conn = ConnectionDB.openConnection();
            int offset = (page - 1) * pageSize;
            cstmt = conn.prepareCall("{call get_students_by_page(?, ?)}");
            cstmt.setInt(1, pageSize);
            cstmt.setInt(2, offset);
            ResultSet rs = cstmt.executeQuery();
            while (rs.next()) {
                Student student = new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDate("dob").toLocalDate(),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getBoolean("sex"),
                        rs.getString("phone"),
                        StatusAccount.valueOf(rs.getString("status").toUpperCase()),
                        rs.getInt("account_id"),
                        rs.getDate("create_at").toLocalDate()
                );
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, cstmt);
        }
        return students;
    }

    @Override
    public Student getStudentById(int id) {
        Connection conn = null;
        CallableStatement cstmt = null;
        Student student = null;
        try {
            conn = ConnectionDB.openConnection();
            cstmt = conn.prepareCall("{call get_student_by_id(?)}");
            cstmt.setInt(1, id);
            ResultSet rs = cstmt.executeQuery();
            if (rs.next()) {
                student = new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDate("dob").toLocalDate(),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getBoolean("sex"),
                        rs.getString("phone"),
                        StatusAccount.valueOf(rs.getString("status").toUpperCase()),
                        rs.getInt("account_id"),
                        rs.getDate("create_at").toLocalDate()
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, cstmt);
        }
        return student;
    }

    @Override
    public List<Student> searchStudents(String keyword, int page, int pageSize, int[] totalRecordsOut) {
        List<Student> students = new ArrayList<Student>();
        Connection conn = null;
        CallableStatement cstmt = null;
        try {
            conn = ConnectionDB.openConnection();
            int offset = (page - 1) * pageSize;
            cstmt = conn.prepareCall("{call search_students_by_name_or_email(?, ?, ?, ?)}");
            cstmt.setString(1, keyword);
            cstmt.setInt(2, pageSize);
            cstmt.setInt(3, offset);
            boolean hasResultSet = cstmt.execute();
            if (hasResultSet) {
                ResultSet rs = cstmt.getResultSet();
                while (rs.next()) {
                    Student student = new Student(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getDate("dob").toLocalDate(),
                            rs.getString("email"),
                            rs.getString("password"),
                            rs.getBoolean("sex"),
                            rs.getString("phone"),
                            StatusAccount.valueOf(rs.getString("status").toUpperCase()),
                            rs.getInt("account_id"),
                            rs.getDate("create_at").toLocalDate()
                    );
                    students.add(student);
                }
            }
            totalRecordsOut[0] = cstmt.getInt(4);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, cstmt);
        }
        return students;
    }

    @Override
    public List<Student> getStudentsSorted(String sortOption, int page, int pageSize) {
        List<Student> students = new ArrayList<Student>();
        Connection conn = null;
        CallableStatement cstmt = null;
        try {
            conn = ConnectionDB.openConnection();
            int offset = (page - 1) * pageSize;
            cstmt = conn.prepareCall("{call get_students_sorted(?, ?, ?)}");
            cstmt.setString(1, sortOption);
            cstmt.setInt(2, pageSize);
            cstmt.setInt(3, offset);
            ResultSet rs = cstmt.executeQuery();
            while (rs.next()) {
                Student student = new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDate("dob").toLocalDate(),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getBoolean("sex"),
                        rs.getString("phone"),
                        StatusAccount.valueOf(rs.getString("status").toUpperCase()),
                        rs.getInt("account_id"),
                        rs.getDate("create_at").toLocalDate()
                );
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, cstmt);
        }
        return students;
    }

    @Override
    public boolean isAccountEmailExist(String email) {
        Connection conn = null;
        CallableStatement cstmt = null;
        try {
            conn = ConnectionDB.openConnection();
            cstmt = conn.prepareCall("{call is_exist_email(?)}");
            cstmt.setString(1, email);
            ResultSet rs = cstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("is_exist") == 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, cstmt);
        }
        return false;
    }

    @Override
    public boolean create(Student student) {
        Connection conn = null;
        CallableStatement cstmt = null;
        try {
            conn = ConnectionDB.openConnection();
            cstmt = conn.prepareCall("{call create_student(?, ?, ?, ?, ?, ?)}");
            cstmt.setString(1, student.getName());
            cstmt.setDate(2, Date.valueOf(student.getDob()));
            cstmt.setBoolean(3, student.isSex());
            cstmt.setString(4, student.getPhone());
            cstmt.setString(5, student.getEmail());
            cstmt.setString(6, student.getPassword());
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
    public boolean update(Student student) {
        Connection conn = null;
        CallableStatement cstmt = null;
        try {
            conn = ConnectionDB.openConnection();
            cstmt = conn.prepareCall("{call update_student(?, ?, ?, ?, ?, ?)}");
            cstmt.setInt(1, student.getId());
            cstmt.setString(2, student.getName());
            cstmt.setDate(3, Date.valueOf(student.getDob()));
            cstmt.setBoolean(4, student.isSex());
            cstmt.setString(5, student.getPhone());
            cstmt.setString(6, student.getStatus().name().toLowerCase());
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
    public boolean delete(Student student) {
        Connection conn = null;
        CallableStatement cstmt = null;
        boolean isDeleted = false;
        try {
            conn = ConnectionDB.openConnection();
            cstmt = conn.prepareCall("{call delete_student(?, ?)}");
            cstmt.setInt(1, student.getId());
            cstmt.registerOutParameter(2, Types.BOOLEAN);
            cstmt.execute();
            isDeleted = cstmt.getBoolean(2);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, cstmt);
        }
        return isDeleted;
    }

    @Override
    public List<RegisteredCourse> getMyRegisteredCourses(int id, int page, int pageSize, int[] totalRecordsOut) {
        List<RegisteredCourse> registeredCourses = new ArrayList<>();
        Connection conn = null;
        CallableStatement cstmt = null;
        try {
            conn = ConnectionDB.openConnection();
            int offset = (page - 1) * pageSize;
            cstmt = conn.prepareCall("{call get_my_registered_courses(?, ?, ?, ?)}");
            cstmt.setInt(1, id);
            cstmt.setInt(2, pageSize);
            cstmt.setInt(3, offset);
            cstmt.registerOutParameter(4, Types.INTEGER);
            boolean hasResultSet = cstmt.execute();
            if (hasResultSet) {
                ResultSet rs = cstmt.getResultSet();
                while (rs.next()) {
                    RegisteredCourse course = new RegisteredCourse(
                            rs.getInt("course_id"),
                            rs.getString("course_name"),
                            rs.getTimestamp("registered_at"),
                            StatusEnrollment.valueOf(rs.getString("status"))
                    );
                    registeredCourses.add(course);
                }
            }
            totalRecordsOut[0] = cstmt.getInt(4);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, cstmt);
        }
        return registeredCourses;
    }

    @Override
    public boolean registerCourse(int studentId, int courseId) {
        Connection conn = null;
        CallableStatement stmt = null;
        boolean isSuccess = false;
        try {
            conn = ConnectionDB.openConnection();
            stmt = conn.prepareCall("{call register_course(?, ?, ?)}");
            stmt.setInt(1, studentId);
            stmt.setInt(2, courseId);
            stmt.registerOutParameter(3, Types.BOOLEAN);
            stmt.execute();
            isSuccess = stmt.getBoolean(3);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, stmt);
        }
        return isSuccess;
    }

    @Override
    public boolean cancelCourseRegistration(int studentId, int courseId) {
        Connection conn = null;
        CallableStatement cstmt = null;
        boolean isCanceled = false;
        try {
            conn = ConnectionDB.openConnection();
            cstmt = conn.prepareCall("{call cancel_course_registration(?, ?, ?)}");
            cstmt.setInt(1, studentId);
            cstmt.setInt(2, courseId);
            cstmt.registerOutParameter(3, Types.BOOLEAN);
            cstmt.execute();
            isCanceled = cstmt.getBoolean(3);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, cstmt);
        }
        return isCanceled;
    }
}
