package ra.edu.presentation;

import ra.edu.validate.Validator;

import java.util.Scanner;

public class AdminMenuUI {
    public static void displayMenu(Scanner sc) {
        do {
            System.out.println("============ MENU ADMIN ============");
            System.out.println(" 1. Quản lý khóa học");
            System.out.println(" 2. Quản lý học viên");
            System.out.println(" 3. Quản lý đăng ký học");
            System.out.println(" 4. Thống kê học viên theo khóa học");
            System.out.println(" 5. Đăng xuất");
            System.out.println("====================================");
            int choice = Validator.validateInputInteger("Nhập lựa chọn: ", sc);
            switch (choice) {
                case 1:
                    CourseUI.menuCourseManager(sc);
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    System.out.println("\u001B[32mĐăng xuất thành công!\u001B[0m");
                    return;
                default:
                    System.out.println("\u001B[31mLựa chọn không hợp lệ!\u001B[0m");
            }
        } while(true);
    }
}
