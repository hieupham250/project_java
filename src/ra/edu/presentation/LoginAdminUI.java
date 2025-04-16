package ra.edu.presentation;

import ra.edu.validate.StringRule;
import ra.edu.validate.Validator;

import java.util.Scanner;

public class LoginAdminUI {
    public static void login(Scanner sc) {
        String username = Validator.validateInputString("Tên đăng nhập: ", sc, new StringRule(1, 50));
        String password = Validator.validateInputString("Mật khẩu: ", sc, new StringRule(1, 255));
    }
}
