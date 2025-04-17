package ra.edu.presentation;

import ra.edu.business.model.Course;
import ra.edu.business.service.course.CourseService;
import ra.edu.business.service.course.CourseServiceImp;
import ra.edu.utils.TableUtils;
import ra.edu.utils.pagination.PaginationConfig;
import ra.edu.utils.pagination.PaginationUtils;
import ra.edu.validate.StringRule;
import ra.edu.validate.Validator;

import java.util.List;
import java.util.Scanner;

import static ra.edu.validate.CourseValidator.isUpdateNameConflict;

public class CourseManager {
    private static final CourseService courseService = new CourseServiceImp();
    public static void menuCourseManager(Scanner sc) {
        do {
            System.out.println("==================================");
            System.out.println(" 1. Hiển thị danh sách khóa học");
            System.out.println(" 2. Thêm mới khóa học");
            System.out.println(" 3. Chỉnh sửa thông tin khóa học");
            System.out.println(" 4. Xóa khóa học");
            System.out.println(" 5. Tìm kiếm theo tên");
            System.out.println(" 6. Sắp xếp theo tên hoặc id");
            System.out.println(" 7. quay về menu chính");
            System.out.println("==================================");
            int choice = Validator.validateInputInteger("Nhập lựa chọn: ", sc);
            switch (choice) {
                case 1:
                    displayCourses(sc);
                    break;
                case 2:
                    createCourse(sc);
                    break;
                case 3:
                    updateCourse(sc);
                    break;
                case 4:
                    deleteCourse(sc);
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

    public static void displayCourses(Scanner sc) {
        int page = 1;
        int pageSize = 5;
        List<Course> courses = courseService.findAll();
        PaginationUtils.paginate(new PaginationConfig<Course>(
                courses,
                page,
                pageSize,
                sc,
                TableUtils::printCourseHeader,
                TableUtils::printCourse
        ));
    }


    public static void createCourse(Scanner sc) {
        Course course = new Course();
        List<Course> existingCourses = courseService.findAll();
        course.inputData(sc, existingCourses);
        if (courseService.create(course)) {
            System.out.println("\u001B[32mThêm khóa học thành công!\u001B[0m");
        } else {
            System.out.println("u001B[31mLỗi khi thêm khóa học.u001B[0m");
        }
    }

    public static void updateCourse(Scanner sc) {
        List<Course> courses = courseService.findAll();
        if (courses.isEmpty()) {
            System.out.println("\u001B[31mHiện không có khóa học nào để cập nhật!\u001B[0m");
            return;
        }
        int id = Validator.validateInputInteger("Nhập mã khóa học muốn cập nhât: ", sc);
        Course existingCourse = courseService.getCourseById(id);
        System.out.println(existingCourse.getInstructor());
        if (existingCourse != null) {
            System.out.println("Thông tin khóa học hiện tại:");
            TableUtils.printCourseHeader();
            TableUtils.printCourse(existingCourse);
            while (true) {
                System.out.println("========= Chọn thông tin muốn cập nhật =========");
                System.out.println("1. Tên khóa học");
                System.out.println("2. Thời lượng (giờ)");
                System.out.println("3. Giảng viên phụ trách");
                System.out.println("4. Thoát");
                int choice = Validator.validateInputInteger("Nhập lựa chọn: ", sc);
                switch (choice) {
                    case 1:
                        String newName = Validator.validateInputString("Nhập tên khóa học mới: ", sc, new StringRule(100, "Tên khóa học không được để trống!"));
                        if (isUpdateNameConflict(newName, existingCourse.getId(), courses)) {
                            System.out.println("\u001B[31mTên khóa học đã tồn tại!\u001B[0m");
                        } else {
                            existingCourse.setName(newName);
                            if (courseService.update(existingCourse)) {
                                System.out.println("\u001B[32mĐã cập nhật tên khóa học thành công!\u001B[0m");
                            } else {
                                System.out.println("\u001B[31mLỗi khi cập nhật tên!\u001B[0m");
                            }
                        }
                        break;
                    case 2:
                        int newDuration = Validator.validateInputInteger("Nhập thời lượng mới (giờ): ", sc);
                        existingCourse.setDuration(newDuration);
                        if (courseService.update(existingCourse)) {
                            System.out.println("\u001B[32mĐã cập nhật thời lượng thành công!\u001B[0m");
                        } else {
                            System.out.println("\u001B[31mLỗi khi cập nhật thời lượng!\u001B[0m");
                        }
                        break;
                    case 3:
                        String newInstructor = Validator.validateInputString("Nhập tên giảng viên mới: ", sc, new StringRule(100, "Tên giảng viên không được để trống!"));
                        existingCourse.setInstructor(newInstructor);
                        if (courseService.update(existingCourse)) {
                            System.out.println("\u001B[32mĐã cập nhật giảng viên thành công!\u001B[0m");
                        } else {
                            System.out.println("\u001B[31mLỗi khi cập nhật giảng viên!\u001B[0m");
                        }
                        break;
                    case 4:
                        return;
                    default:
                        System.out.println("\u001B[31mLựa chọn không hợp lệ!\u001B[0m");
                }
            }
        } else {
            System.out.println("\u001B[31mMã khóa học không tồn tại!\u001B[0m");
        }
    }

    public static void deleteCourse(Scanner sc) {
        List<Course> courses = courseService.findAll();
        if (courses.isEmpty()) {
            System.out.println("\u001B[31mHiện không có khóa học nào để xóa!\u001B[0m");
            return;
        }
        int id = Validator.validateInputInteger("Nhập mã khóa học muốn xóa: ", sc);
        Course course = courseService.getCourseById(id);
        if (course != null) {
            System.out.println("Bạn có chắc chắn muốn xóa khóa học sau không?");
            TableUtils.printCourseHeader();
            TableUtils.printCourse(course);
            while (true) {
                System.out.println("1. xác nhận");
                System.out.println("2. Hủy");
                int choice = Validator.validateInputInteger("Nhập lựa chọn: ", sc);
                switch (choice) {
                    case 1:
                        if (courseService.delete(course)) {
                            System.out.println("\u001B[32mĐã xóa khóa học thành công!\u001B[0m");
                        } else {
                            System.out.println("\u001B[31mLỗi khi xóa khóa học!\u001B[0m");
                        }
                        return;
                    case 2:
                        return;
                    default:
                        System.out.println("\u001B[31mLựa chọn không hợp lệ!\u001B[0m");
                }
            }
        } else {
            System.out.println("\u001B[31mMã khóa học không tồn tại!\u001B[0m");
        }
    }
}
