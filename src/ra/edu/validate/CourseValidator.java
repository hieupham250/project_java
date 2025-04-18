package ra.edu.validate;

import ra.edu.business.model.Course;
import ra.edu.business.service.course.CourseService;

import java.util.List;

public class CourseValidator {
    public static boolean isNameExisted(String name, CourseService courseService) {
        return courseService.isCourseNameExist(name);
    }

    public static boolean isUpdateNameConflict(String newName, int currentCourseId, CourseService courseService) {
        // Kiểm tra nếu tên tồn tại và không phải của course hiện tại
        return courseService.getCoursesSorted("name").stream()
                .anyMatch(c -> c.getName().equalsIgnoreCase(newName.trim()) && c.getId() != currentCourseId);
    }
}
