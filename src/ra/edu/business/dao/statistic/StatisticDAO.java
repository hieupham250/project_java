package ra.edu.business.dao.statistic;

import ra.edu.business.model.Statistic;

import java.util.List;

public interface StatisticDAO {
    Statistic getTotalCoursesAndStudents();
    List<Statistic> getStudentsCountByCourse(int page, int pageSize, int[] totalRecordsOut);
    List<Statistic> getTop5MostPopularCourses();
    List<Statistic> getCoursesWithMoreThan10Students(int page, int pageSize, int[] totalRecordsOut);
}
