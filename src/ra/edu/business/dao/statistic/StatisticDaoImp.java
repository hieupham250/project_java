package ra.edu.business.dao.statistic;

import ra.edu.business.config.ConnectionDB;
import ra.edu.business.model.Statistic;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StatisticDaoImp implements StatisticDAO {
    @Override
    public Statistic getTotalCoursesAndStudents() {
        Connection conn = null;
        CallableStatement cstmt = null;
        Statistic result = null;
        try {
            conn = ConnectionDB.openConnection();
            cstmt = conn.prepareCall("{call get_total_courses_and_students(?, ?)}");
            cstmt.registerOutParameter(1, Types.INTEGER);
            cstmt.registerOutParameter(2, Types.INTEGER);
            cstmt.execute();
            result = new Statistic(cstmt.getInt(1), cstmt.getInt(2));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, cstmt);
        }
        return result;
    }

    @Override
    public List<Statistic> getStudentsCountByCourse(int page, int pageSize, int[] totalRecordsOut) {
        Connection conn = null;
        CallableStatement cstmt = null;
        List<Statistic> result = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            int offset = (page - 1) * pageSize;
            cstmt = conn.prepareCall("{call get_students_count_by_course(?, ?, ?)}");
            cstmt.setInt(1, pageSize);
            cstmt.setInt(2, offset);
            cstmt.registerOutParameter(3, Types.INTEGER);
            boolean hasResultSet = cstmt.execute();
            if (hasResultSet) {
                ResultSet rs = cstmt.getResultSet();
                while (rs.next()) {
                    Statistic statistic = new Statistic(
                            rs.getInt("course_id"),
                            rs.getString("course_name"),
                            rs.getInt("total_students")
                    );
                    result.add(statistic);
                }
            }
            totalRecordsOut[0] = cstmt.getInt(3);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, cstmt);
        }
        return result;
    }

    @Override
    public List<Statistic> getTop5MostPopularCourses() {
        Connection conn = null;
        CallableStatement cstmt = null;
        List<Statistic> result = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            cstmt = conn.prepareCall("{call get_top_5_most_popular_courses()}");
            ResultSet rs = cstmt.executeQuery();
            while (rs.next()) {
                Statistic statistic = new Statistic(
                        rs.getInt("course_id"),
                        rs.getString("course_name"),
                        rs.getInt("total_students")
                );
                result.add(statistic);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, cstmt);
        }
        return result;
    }

    @Override
    public List<Statistic> getCoursesWithMoreThan10Students(int page, int pageSize, int[] totalRecordsOut) {
        Connection conn = null;
        CallableStatement cstmt = null;
        List<Statistic> result = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            int offset = (page - 1) * pageSize;
            cstmt = conn.prepareCall("{call get_courses_with_more_than_10_students(?, ?, ?)}");
            cstmt.setInt(1, pageSize);
            cstmt.setInt(2, offset);
            cstmt.registerOutParameter(3, Types.INTEGER);
            boolean hasResultSet = cstmt.execute();
            if (hasResultSet) {
                ResultSet rs = cstmt.getResultSet();
                while (rs.next()) {
                    Statistic statistic = new Statistic(
                            rs.getInt("course_id"),
                            rs.getString("course_name"),
                            rs.getInt("total_students")
                    );
                    result.add(statistic);
                }
            }
            totalRecordsOut[0] = cstmt.getInt(3);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, cstmt);
        }
        return result;
    }
}
