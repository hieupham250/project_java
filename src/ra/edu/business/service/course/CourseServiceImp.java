package ra.edu.business.service.course;

import ra.edu.business.dao.course.CourseDao;
import ra.edu.business.dao.course.CourseDaoImp;
import ra.edu.business.model.Course;

import java.util.List;

public class CourseServiceImp implements CourseService {
    private CourseDao courseDao;

    public CourseServiceImp() {
        courseDao = new CourseDaoImp();
    }

    @Override
    public List<Course> getCourses() {
        return courseDao.getCourses();
    }

    @Override
    public boolean createCourse(Course course) {
        return courseDao.createCourse(course);
    }

    @Override
    public boolean updateCourse(Course course) {
        return courseDao.updateCourse(course);
    }
}
