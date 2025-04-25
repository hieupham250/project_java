package ra.edu.business.service.statistic;

import ra.edu.business.model.Statistic;

import java.util.List;

public interface StatisticService {
    Statistic getTotalCoursesAndStudents();
    List<Statistic> getStudentsCountByCourse(int page, int pageSize, int[] totalRecordsOut);
    List<Statistic> getTop5MostPopularCourses();
    List<Statistic> getCoursesWithMoreThan10Students(int page, int pageSize, int[] totalRecordsOut);
}
