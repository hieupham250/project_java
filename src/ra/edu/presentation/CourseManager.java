package ra.edu.presentation;

import ra.edu.business.model.Course;
import ra.edu.business.service.course.CourseService;
import ra.edu.business.service.course.CourseServiceImp;
import ra.edu.validate.StringRule;
import ra.edu.validate.Validator;

import java.util.List;
import java.util.Scanner;

import static ra.edu.validate.CourseValidator.isUpdateNameConflict;

public class CourseManager {
    private static final CourseService courseService = new CourseServiceImp();

    public static void menuCourseManager(Scanner sc) {
        do {
            System.out.println("1. Hiển thị danh sách khóa học");
            System.out.println("2. Thêm mới khóa học");
            System.out.println("3. Chỉnh sửa thông tin khóa học");
            System.out.println("4. Xóa khóa học");
            System.out.println("5. Tìm kiếm theo tên");
            System.out.println("6. Sắp xếp theo tên hoặc id");
            System.out.println("7. quay về menu chính");
            int choice = Validator.validateInputInteger("Nhập lựa chọn: ", sc);
            switch (choice) {
                case 1:
                    displayCourses();
                    break;
                case 2:
                    createCourse(sc);
                    break;
                case 3:
                    updateCourse(sc);
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    return;
                default:
                    System.out.println("\u001B[31mLựa chọn không hợp lệ!\u001B[0m");
            }
        } while (true);
    }

    public static void displayCourses() {
        List<Course> courses = courseService.getCourses();
        if (courses.isEmpty()) {
            System.out.println("\u001B[31mKhông có khóa học nào!\u001B[0m");
        } else {
            for (Course course : courses) {
                System.out.println(course);
            }
        }
    }

    public static void createCourse(Scanner sc) {
        Course course = new Course();
        List<Course> existingCourses = courseService.getCourses();
        course.inputData(sc, existingCourses);
        if (courseService.createCourse(course)) {
            System.out.println("\u001B[32mThêm khóa học thành công!\u001B[0m");
        } else {
            System.out.println("u001B[31mLỗi khi thêm khóa học.u001B[0m");
        }
    }

    public static void updateCourse(Scanner sc) {
        int id = Validator.validateInputInteger("Nhập mã khóa học muốn cập nhât: ", sc);
        sc.nextLine();
        List<Course> existingCourses = courseService.getCourses();
        Course existingCourse = courseService.getCourseById(id);
        if (existingCourse != null) {
            System.out.println("Thông tin khóa học hiện tại:");
            System.out.println(existingCourse);
            boolean isUpdating = true;
            while (isUpdating) {
                System.out.println("========= Chọn thông tin muốn cập nhật =========");
                System.out.println("1. Tên khóa học");
                System.out.println("2. Thời lượng (giờ)");
                System.out.println("3. Giảng viên phụ trách");
                System.out.println("4. Thoát");
                int choice = Validator.validateInputInteger("Nhập lựa chọn: ", sc);
                switch (choice) {
                    case 1:
                        String newName = Validator.validateInputString("Nhập tên khóa học mới: ", sc, new StringRule(1, 100));
                        if (isUpdateNameConflict(newName, existingCourse.getId(), existingCourses)) {
                            System.out.println("\u001B[31mTên khóa học đã tồn tại!\u001B[0m");
                        } else {
                            existingCourse.setName(newName);
                            if (courseService.updateCourse(existingCourse)) {
                                System.out.println("\u001B[32mĐã cập nhật tên khóa học thành công!\u001B[0m");
                            } else {
                                System.out.println("\u001B[31mLỗi khi cập nhật tên!\u001B[0m");
                            }
                        }
                        break;
                    case 2:
                        int newDuration = Validator.validateInputInteger("Nhập thời lượng mới (giờ): ", sc);
                        existingCourse.setDuration(newDuration);
                        if (courseService.updateCourse(existingCourse)) {
                            System.out.println("\u001B[32mĐã cập nhật thời lượng thành công!\u001B[0m");
                        } else {
                            System.out.println("\u001B[31mLỗi khi cập nhật thời lượng!\u001B[0m");
                        }
                        break;
                    case 3:
                        String newInstructor = Validator.validateInputString("Nhập tên giảng viên mới: ", sc, new StringRule(1, 100));
                        existingCourse.setInstructor(newInstructor);
                        if (courseService.updateCourse(existingCourse)) {
                            System.out.println("\u001B[32mĐã cập nhật giảng viên thành công!\u001B[0m");
                        } else {
                            System.out.println("\u001B[31mLỗi khi cập nhật giảng viên!\u001B[0m");
                        }
                        break;
                    case 4:
                        isUpdating = false;
                    default:
                        System.out.println("\u001B[31mLựa chọn không hợp lệ!\u001B[0m");
                }
            }
        } else {
            System.out.println("\u001B[31mMã khóa học không tồn tại!\u001B[0m");
        }
    }
}
