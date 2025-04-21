package ra.edu.business.dao.course;

import ra.edu.business.dao.AppDao;
import ra.edu.business.model.Course;

import java.util.List;

public interface CourseDao extends AppDao<Course> {
    @Override
    List<Course> findAll();

    List<Course> getCoursesByPage(int page, int pageSize);

    Course getCourseById(int id);

    List<Course> searchCoursesByName(String keyword, int page, int pageSize, int[] totalRecordsOut);

    List<Course> getCoursesSorted(String sortOption, int page, int pageSize);

    boolean isCourseNameExist(String name);

    @Override
    boolean create(Course course);

    @Override
    boolean update(Course course);

    @Override
    boolean delete(Course course);
}
