package ra.edu.business.dao.course;

import ra.edu.business.config.ConnectionDB;
import ra.edu.business.model.Course;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseDaoImp implements CourseDao {
    @Override
    public List<Course> findAll() {
        List<Course> courses = new ArrayList<Course>();
        Connection conn = null;
        CallableStatement cstmt = null;
        try {
            conn = ConnectionDB.openConnection();
            cstmt = conn.prepareCall("{call get_courses()}");
            ResultSet rs = cstmt.executeQuery();
            while (rs.next()) {
                Course course = new Course(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("duration"),
                        rs.getString("instructor"),
                        rs.getDate("create_at").toLocalDate()
                );
                courses.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, cstmt);
        }
        return courses;
    }

    @Override
    public List<Course> getCoursesByPage(int page, int pageSize) {
        List<Course> courses = new ArrayList<Course>();
        Connection conn = null;
        CallableStatement cstmt = null;
        try {
            conn = ConnectionDB.openConnection();
            int offset = (page - 1) * pageSize;
            cstmt = conn.prepareCall("{call get_courses_by_page(?, ?)}");
            cstmt.setInt(1, pageSize);
            cstmt.setInt(2, offset);
            ResultSet rs = cstmt.executeQuery();
            while (rs.next()) {
                Course course = new Course(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("duration"),
                        rs.getString("instructor"),
                        rs.getDate("create_at").toLocalDate()
                );
                courses.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, cstmt);
        }
        return courses;
    }

    @Override
    public Course getCourseById(int id) {
        Connection conn = null;
        CallableStatement cstmt = null;
        Course course = null;
        try {
            conn = ConnectionDB.openConnection();
            cstmt = conn.prepareCall("{call get_course_by_id(?)}");
            cstmt.setInt(1, id);
            ResultSet rs = cstmt.executeQuery();
            if (rs.next()) {
                course = new Course(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("duration"),
                        rs.getString("instructor"),
                        rs.getDate("create_at").toLocalDate()
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, cstmt);
        }
        return course;
    }

    @Override
    public List<Course> searchCoursesByName(String name) {
        List<Course> courses = new ArrayList<Course>();
        Connection conn = null;
        CallableStatement cstmt = null;
        try {
            conn = ConnectionDB.openConnection();
            cstmt = conn.prepareCall("{call get_courses_by_name(?)}");
            cstmt.setString(1, name);
            ResultSet rs = cstmt.executeQuery();
            while (rs.next()) {
                Course course = new Course(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("duration"),
                        rs.getString("instructor"),
                        rs.getDate("create_at").toLocalDate()
                );
                courses.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, cstmt);
        }
        return courses;
    }

    @Override
    public List<Course> getCoursesSorted(String sort_option) {
        List<Course> courses = new ArrayList<Course>();
        Connection conn = null;
        CallableStatement cstmt = null;
        try {
            conn = ConnectionDB.openConnection();
            cstmt = conn.prepareCall("{call get_courses_sorted(?)}");
            cstmt.setString(1, sort_option);
            ResultSet rs = cstmt.executeQuery();
            while (rs.next()) {
                Course course = new Course(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("duration"),
                        rs.getString("instructor"),
                        rs.getDate("create_at").toLocalDate()
                );
                courses.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, cstmt);
        }
        return courses;
    }

    @Override
    public boolean isCourseNameExist(String name) {
        Connection conn = null;
        CallableStatement cstmt = null;
        try {
            conn = ConnectionDB.openConnection();
            cstmt = conn.prepareCall("{call is_exist_name(?)}");
            cstmt.setString(1, name);
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

    public boolean checkCourseHasStudents(int id) {
        Connection conn = null;
        CallableStatement cstmt = null;
        try {
            conn = ConnectionDB.openConnection();
            cstmt = conn.prepareCall("{call check_course_has_students(?)}");
            cstmt.setInt(1, id);
            ResultSet rs = cstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("has_students") == 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, cstmt);
        }
        return false;
    }

    @Override
    public boolean create(Course course) {
        Connection conn = null;
        CallableStatement cstmt = null;
        try {
            conn = ConnectionDB.openConnection();
            cstmt = conn.prepareCall("{call create_course(?, ?, ?)}");
            cstmt.setString(1, course.getName());
            cstmt.setInt(2, course.getDuration());
            cstmt.setString(3, course.getInstructor());
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
    public boolean update(Course course) {
        Connection conn = null;
        CallableStatement cstmt = null;
        try {
            conn = ConnectionDB.openConnection();
            cstmt = conn.prepareCall("{call update_course(?, ?, ?, ?)}");
            cstmt.setInt(1, course.getId());
            cstmt.setString(2, course.getName());
            cstmt.setInt(3, course.getDuration());
            cstmt.setString(4, course.getInstructor());
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
    public boolean delete(Course course) {
        Connection conn = null;
        CallableStatement cstmt = null;
        try {
            conn = ConnectionDB.openConnection();
            cstmt = conn.prepareCall("{call delete_course(?)}");
            cstmt.setInt(1, course.getId());
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
