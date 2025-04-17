package ra.edu.validate;

import ra.edu.business.model.Course;

import java.util.List;

public class CourseValidator {
    public static boolean isNameExisted(String name, List<Course> existingCourses) {
        for (Course course : existingCourses) {
            if (course.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isUpdateNameConflict(String newName, int currentCourseId, List<Course> existingCourses) {
        for (Course c : existingCourses) {
            if (c.getName().equalsIgnoreCase(newName.trim()) && c.getId() != currentCourseId) {
                return true;
            }
        }
        return false;
    }
}
