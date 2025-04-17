package ra.edu.business.dao.course;

import ra.edu.business.dao.AppDao;
import ra.edu.business.model.Course;

import java.util.List;

public interface CourseDao extends AppDao<Course> {
    List<Course> findAll();
    List<Course> getCoursesByPage(int page, int pageSize);
    Course getCourseById(int id);
    boolean create(Course course);
    boolean update(Course course);
    boolean delete(Course course);
}
