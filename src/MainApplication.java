import ra.edu.business.model.Account;
import ra.edu.presentation.AdminMenuUI;
import ra.edu.presentation.LoginUI;
import ra.edu.presentation.StudentMenuUI;

import java.util.Scanner;

public class MainApplication {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Account loggedInAccount;

        do {
            loggedInAccount = LoginUI.login(sc);
            if (loggedInAccount != null) {
                switch (loggedInAccount.getRole()) {
                    case ADMIN:
                        System.out.println("\u001B[32mĐăng nhập thành công. Chào mừng Quản trị viên!\u001B[0m");
                        AdminMenuUI.displayMenu(sc);
                        break;
                    case STUDENT:
                        System.out.println("\u001B[32mĐăng nhập thành công. Chào mừng Học viên!\u001B[0m");
                        StudentMenuUI.displayMenu(sc);
                        break;
                    default:
                        System.out.println("\u001B[31mKhông xác định quyền hạn!\u001B[0m");
                        break;
                }
                break;
            } else {
                System.out.println("\u001B[31mĐăng nhập không thành công. Email hoặc mật khẩu không chính xác!\u001B[0m");
            }
        } while (true);
    }
}
