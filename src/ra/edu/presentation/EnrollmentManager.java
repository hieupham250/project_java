package ra.edu.presentation;

import ra.edu.business.service.course.CourseService;
import ra.edu.business.service.course.CourseServiceImp;
import ra.edu.business.service.enrollment.EnrollmentService;
import ra.edu.business.service.enrollment.EnrollmentServiceImp;
import ra.edu.business.service.student.StudentService;
import ra.edu.business.service.student.StudentServiceImp;
import ra.edu.datatype.PaginationOption;
import ra.edu.utils.PaginationUtils;
import ra.edu.validate.Validator;

import java.util.Scanner;

public class EnrollmentManager {
    public static StudentService studentService = new StudentServiceImp();
    public static CourseService courseService = new CourseServiceImp();
    public static EnrollmentService enrollmentService = new EnrollmentServiceImp();

    public static void menuEnrollmentManager(Scanner sc) {
        do {
            System.out.println("==================================");
            System.out.println(" 1. Hiển thị danh sách học viên đăng ký theo khóa học");
            System.out.println(" 2. Duyệt học viên đăng ký");
            System.out.println(" 3. Xóa học viên khỏi khóa học");
            System.out.println(" 4. quay về menu chính");
            System.out.println("==================================");
            int choice = Validator.validateInputInteger("Nhập lựa chọn: ", sc);
            switch (choice) {
                case 1:
                    displayStudentsByCourse(sc);
                    break;
                case 2:
                    approveStudentEnrollment(sc);
                    break;
                case 3:
                    deleteStudentFromCourse(sc);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("\u001B[31mLựa chọn không hợp lệ!\u001B[0m");
            }
        } while (true);
    }

    public static void displayStudentsByCourse(Scanner sc) {
        int courseId = Validator.validateInputInteger("Nhập mã khóa học cần xem danh sách đăng ký: ", sc);
        if (courseService.getCourseById(courseId) == null) {
            System.out.println("\u001B[31mMã khóa học không tồn tại!\u001B[0m");
            return;
        }
        PaginationUtils<EnrollmentService> paginator = new PaginationUtils<>(
                courseId,
                PaginationOption.COURSE_STUDENT_REGISTERED,
                enrollmentService
        );
        paginator.paginate(sc);
    }

    public static void approveStudentEnrollment(Scanner sc) {
        int studentId = Validator.validateInputInteger("Nhập mã học viên: ", sc);
        if (studentService.getStudentById(studentId) == null) {
            System.out.println("\u001B[31mMã học viên không tồn tại!\u001B[0m");
            return;
        }
        int courseId = Validator.validateInputInteger("Nhập mã khóa học: ", sc);
        if (courseService.getCourseById(courseId) == null) {
            System.out.println("\u001B[31mMã khóa học không tồn tại!\u001B[0m");
            return;
        }
        do {
            System.out.println("Chọn hành động:");
            System.out.println("1. Xác nhận");
            System.out.println("2. Từ chối");
            System.out.println("3. Quay lại menu chính");
            int option = Validator.validateInputInteger("Nhập lựa chọn: ", sc);
            String status = null;
            switch (option) {
                case 1:
                    status = "CONFIRM";
                    break;
                case 2:
                    status = "DENIED";
                    break;
                case 3:
                    System.out.println("Đã hủy thao tác duyệt học viên.");
                    return;
                default:
                    System.out.println("\u001B[31mLựa chọn không hợp lệ. Vui lòng thử lại!\u001B[0m");
            }
            boolean isSuccess = enrollmentService.approveOrDenyRegistration(studentId, courseId, status);
            if (isSuccess) {
                System.out.println("\u001B[32mĐã cập nhật trạng thái đăng ký của học viên thành công!\u001B[0m");
                return;
            } else {
                System.out.println("\u001B[31mKhông thể cập nhật. Có thể học viên chưa đăng ký hoặc đã được xử lý!\u001B[0m");
                return;
            }
        } while (true);
    }

    public static void deleteStudentFromCourse(Scanner sc) {
        int studentId = Validator.validateInputInteger("Nhập mã học viên cần xóa: ", sc);
        if (studentService.getStudentById(studentId) == null) {
            System.out.println("\u001B[31mMã học viên không tồn tại!\u001B[0m");
            return;
        }
        int courseId = Validator.validateInputInteger("Nhập mã khóa học: ", sc);
        if (courseService.getCourseById(courseId) == null) {
            System.out.println("\u001B[31mMã khóa học không tồn tại!\u001B[0m");
            return;
        }
        System.out.println("Bạn có chắc chắn muốn xóa học viên này khỏi khóa học?");
        while (true) {
            System.out.println("1. xác nhận");
            System.out.println("2. Hủy");
            int choice = Validator.validateInputInteger("Nhập lựa chọn: ", sc);
            switch (choice) {
                case 1:
                    if (enrollmentService.deleteEnrollment(studentId, courseId)) {
                        System.out.println("\u001B[32mĐã xóa học viên khỏi khóa học thành công!\u001B[0m");
                    } else {
                        System.out.println("\u001B[31mKhông thể xóa. Học viên có thể chưa đăng ký hoặc trạng thái không phù hợp để xóa!\u001B[0m");
                    }
                    return;
                case 2:
                    return;
                default:
                    System.out.println("\u001B[31mLựa chọn không hợp lệ!\u001B[0m");
            }
        }
    }
}
