package ra.edu.presentation;

import ra.edu.business.model.Admin;
import ra.edu.business.model.Student;
import ra.edu.business.service.auth.AuthService;
import ra.edu.business.service.auth.AuthServiceImp;
import ra.edu.validate.StringRule;
import ra.edu.validate.Validator;

import java.util.Scanner;

public class Authentication {
    private static final AuthService authService = new AuthServiceImp();
    public static void loginAdmin(Scanner sc) {
        String username = Validator.validateInputString("Tên đăng nhập: ", sc, new StringRule(1, 50));
        String password = Validator.validateInputString("Mật khẩu: ", sc, new StringRule(1, 255));

        Admin admin = authService.loginAdmin(username, password);

        if (admin != null) {
            System.out.println("\u001B[32mĐăng nhập thành công! Chào " + admin.getUsername() + "\u001B[0m");
            AdminMenuUI.displayMenu(sc);
        } else {
            System.out.println("\u001B[31mĐăng nhập không thành công! tên đăng nhập hoặc mật khẩu không chính xác.\u001B[0m");
        }
    }

    public static void loginStudent(Scanner sc) {
        String email = Validator.validateInputString("email: ", sc, new StringRule(1, 50));
        String password = Validator.validateInputString("Mật khẩu: ", sc, new StringRule(1, 255));

        Student student = authService.loginStudent(email, password);

        if (student != null) {
            System.out.println("\u001B[32mĐăng nhập thành công! Chào " + student.getName() + "\u001B[0m");
            StudentMenuUI.displayMenu(sc);
        } else {
            System.out.println("\u001B[31mĐăng nhập không thành công! email hoặc mật khẩu không chính xác.\u001B[0m");
        }
    }

    public static void logout() {
        authService.logout();
        System.out.println("\u001B[32mĐăng xuất thành công!\u001B[0m");
    }
}
