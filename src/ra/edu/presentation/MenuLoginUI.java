package ra.edu.presentation;

import ra.edu.validate.Validator;

import java.util.Scanner;

public class MenuLoginUI {
    public static void displayMenu(Scanner sc) {
        do {
            System.out.println("========= HỆ THỐNG QUẢN LÝ ĐÀO TẠO =========");
            System.out.println("1. Đăng nhập với tư cách Quản trị viên");
            System.out.println("2. Đăng nhập với tư cách Học viên");
            System.out.println("3. Thoát");
            System.out.println("============================================");
            int choice = Validator.validateInputInteger("Nhập lựa chọn: ", sc);
            switch (choice) {
                case 1:
                    LoginAdminUI.login(sc);
                    break;
                case 2:
                    LoginStudentUI.login(sc);
                    break;
                case 3:
                    System.out.println("Kết thúc chương trình.");
                    sc.close();
                    System.exit(0);
                default:
                    System.out.println("\u001B[31mLựa chọn không hợp lệ\u001B[0m");
            }
        } while(true);
    }
}
