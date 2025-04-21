package ra.edu.presentation;

import ra.edu.business.model.Account;
import ra.edu.business.service.course.CourseService;
import ra.edu.business.service.course.CourseServiceImp;
import ra.edu.business.service.student.StudentService;
import ra.edu.business.service.student.StudentServiceImp;
import ra.edu.datatype.PaginationOption;
import ra.edu.utils.PaginationUtils;
import ra.edu.validate.Validator;

import java.util.Scanner;

public class StudentMenuUI {
    public static CourseService courseService = new CourseServiceImp();
    public static StudentService studentService = new StudentServiceImp();

    public static void displayMenu(Scanner sc) {
        do {
            System.out.println("========= MENU HỌC VIÊN =========");
            System.out.println(" 1. Xem danh sách khóa học");
            System.out.println(" 2. Tìm kiếm khóa học");
            System.out.println(" 3. Đăng ký khóa học");
            System.out.println(" 4. Xem khóa học đã đăng ký");
            System.out.println(" 5. Hủy đăng ký (nếu chưa bắt đầu)");
            System.out.println(" 6. Đổi mật khẩu");
            System.out.println(" 7. Đăng xuất");
            System.out.println("=================================");
            int choice = Validator.validateInputInteger("Nhập lựa chọn: ", sc);
            switch (choice) {
                case 1:
                    CourseManager.displayCourses(sc);
                    break;
                case 2:
                    CourseManager.searchCourseByName(sc);
                    break;
                case 3:
                    registerCourse(sc);
                    break;
                case 4:
                    displayCourseRegistration(sc);
                    break;
                case 5:
                    cancelCourseRegistration(sc);
                    break;
                case 6:
                    break;
                case 7:
                    LoginUI.logout();
                    return;
                default:
                    System.out.println("\u001B[31mLựa chọn không hợp lệ!\u001B[0m");
            }
        } while (true);
    }

    public static void registerCourse(Scanner sc) {
        int courseId = Validator.validateInputInteger("Nhập mã khóa học muốn đăng ký: ", sc);
        if (courseService.getCourseById(courseId) == null) {
            System.out.println("\u001B[31mMã khóa học không tồn tại!\u001B[0m");
            return;
        }
        Account currentAccount = LoginUI.getLoggedInAccount();
        int studentId = currentAccount.getStudentId();
        if (studentService.registerCourse(studentId, courseId)) {
            System.out.println("\u001B[32mĐăng ký khóa học thành công!\u001B[0m");
        } else {
            System.out.println("\u001B[31mBạn đã đăng ký khóa học này hoặc xảy ra lỗi!\u001B[0m");
        }
    }

    public static void displayCourseRegistration(Scanner sc) {
        Account currentAccount = LoginUI.getLoggedInAccount();
        int studentId = currentAccount.getStudentId();
        PaginationUtils<StudentService> pagination = new PaginationUtils<>(studentId, PaginationOption.COURSE_REGISTERED, studentService);
        pagination.paginate(sc);
    }

    public static void cancelCourseRegistration(Scanner sc) {
        int courseId = Validator.validateInputInteger("Nhập mã khóa học muốn hủy đăng ký: ", sc);
        if (courseService.getCourseById(courseId) == null) {
            System.out.println("\u001B[31mMã khóa học không tồn tại!\u001B[0m");
            return;
        }
        Account currentAccount = LoginUI.getLoggedInAccount();
        int studentId = currentAccount.getStudentId();
        if (studentService.cancelCourseRegistration(studentId, courseId)) {
            System.out.println("\u001B[32mHủy đăng ký khóa học thành công!\u001B[0m");
        } else {
            System.out.println("\u001B[31mHủy đăng ký khóa học không thành công hoặc khóa học đã bắt đầu!\u001B[0m");
        }
    }
}
