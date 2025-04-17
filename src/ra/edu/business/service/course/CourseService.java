package ra.edu.business.service.course;

import ra.edu.business.model.Course;

import java.util.List;

public interface CourseService {
    List<Course> getCourses();
    Course getCourseById(int id);
    boolean createCourse(Course course);
    boolean updateCourse(Course course);
}
