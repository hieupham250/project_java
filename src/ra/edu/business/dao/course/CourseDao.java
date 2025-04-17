package ra.edu.business.dao.course;

import ra.edu.business.model.Course;

import java.util.List;

public interface CourseDao {
    List<Course> getCourses();
    List<Course> getCoursesByPage(int page, int pageSize);
    Course getCourseById(int id);
    boolean createCourse(Course course);
    boolean updateCourse(Course course);
}
