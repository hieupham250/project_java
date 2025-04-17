package ra.edu.business.service.course;

import ra.edu.business.model.Course;
import ra.edu.business.service.AppService;

import java.util.List;

public interface CourseService extends AppService<Course> {
    List<Course> findAll();
    List<Course> getCoursesByPage(int page, int pageSize);
    Course getCourseById(int id);
    boolean create(Course course);
    boolean update(Course course);
    boolean delete(Course course);
}
