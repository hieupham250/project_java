package ra.edu.business.service.statistic;

import ra.edu.business.dao.statistic.StatisticDAO;
import ra.edu.business.dao.statistic.StatisticDaoImp;
import ra.edu.business.model.Statistic;

import java.util.List;

public class StatisticServiceImp implements StatisticService {
    private final StatisticDAO statisticDAO;

    public StatisticServiceImp() {
        statisticDAO = new StatisticDaoImp();
    }

    @Override
    public Statistic getTotalCoursesAndStudents() {
        return statisticDAO.getTotalCoursesAndStudents();
    }

    @Override
    public List<Statistic> getStudentsCountByCourse(int page, int pageSize, int[] totalRecordsOut) {
        return statisticDAO.getStudentsCountByCourse(page, pageSize, totalRecordsOut);
    }

    @Override
    public List<Statistic> getTop5MostPopularCourses() {
        return statisticDAO.getTop5MostPopularCourses();
    }

    @Override
    public List<Statistic> getCoursesWithMoreThan10Students(int page, int pageSize, int[] totalRecordsOut) {
        return statisticDAO.getCoursesWithMoreThan10Students(page, pageSize, totalRecordsOut);
    }
}
