package ra.edu.validate;

import ra.edu.business.model.Course;
import ra.edu.business.service.course.CourseService;

import java.util.List;

public class CourseValidator {
    public static boolean isNameExisted(String name, CourseService courseService) {
        return courseService.isCourseNameExist(name);
    }
}
