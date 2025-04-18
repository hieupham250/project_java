package ra.edu.presentation;

import ra.edu.business.model.Course;
import ra.edu.validate.Validator;

import java.util.List;
import java.util.Scanner;

public class StudentMenuUI {
    public static void displayMenu(Scanner sc) {
        do {
            System.out.println("========= MENU HỌC VIÊN =========");
            System.out.println(" 1. Xem danh sách khóa học");
            System.out.println(" 2. Đăng ký khóa học");
            System.out.println(" 3. Xem khóa học đã đăng ký");
            System.out.println(" 4. Hủy đăng ký (nếu chưa bắt đầu)");
            System.out.println(" 5. Đổi mật khẩu");
            System.out.println(" 6. Đăng xuất");
            System.out.println("=================================");
            int choice = Validator.validateInputInteger("Nhập lựa chọn: ", sc);
            switch (choice) {
                case 1:
                    List<Course> courses = CourseManager.courseService.findAll();
                    CourseManager.paginate(courses, sc);
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    LoginUI.logout();
                    return;
                default:
                    System.out.println("\u001B[31mLựa chọn không hợp lệ!\u001B[0m");
            }
        } while (true);
    }
}
