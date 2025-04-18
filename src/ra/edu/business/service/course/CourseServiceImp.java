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
    public List<Course> findAll() {
        return courseDao.findAll();
    }

    @Override
    public List<Course> getCoursesByPage(int page, int pageSize) {
        return courseDao.getCoursesByPage(page, pageSize);
    }

    @Override
    public Course getCourseById(int id) {
        return courseDao.getCourseById(id);
    }

    @Override
    public List<Course> searchCoursesByName(String name, int page, int pageSize) {
        return courseDao.searchCoursesByName(name, page, pageSize);
    }

    @Override
    public List<Course> getCoursesSorted(String sort_option, int page, int pageSize) {
        return courseDao.getCoursesSorted(sort_option, page, pageSize);
    }

    @Override
    public boolean isCourseNameExist(String name) {
        return courseDao.isCourseNameExist(name);
    }

    @Override
    public boolean checkCourseHasStudents(int id) {
        return courseDao.checkCourseHasStudents(id);
    }

    @Override
    public boolean create(Course course) {
        return courseDao.create(course);
    }

    @Override
    public boolean update(Course course) {
        return courseDao.update(course);
    }

    @Override
    public boolean delete(Course course) {
        return courseDao.delete(course);
    }
}
