package ra.edu.business.service.course;

import ra.edu.business.model.Course;
import ra.edu.business.service.AppService;

import java.util.List;

public interface CourseService extends AppService<Course> {
    @Override
    List<Course> findAll();

    List<Course> getCoursesByPage(int page, int pageSize);

    Course getCourseById(int id);

    List<Course> searchCoursesByName(String name);

    List<Course> getCoursesSorted(String sort_option);

    boolean isCourseNameExist(String name);

    boolean checkCourseHasStudents(int id);

    @Override
    boolean create(Course course);

    @Override
    boolean update(Course course);

    @Override
    boolean delete(Course course);
}
