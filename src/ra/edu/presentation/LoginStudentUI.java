package ra.edu.presentation;

import ra.edu.validate.StringRule;
import ra.edu.validate.Validator;

import java.util.Scanner;

public class LoginStudentUI {
    public static void login(Scanner sc) {
        String email = Validator.validateInputString("email: ", sc, new StringRule(1, 50));
        String password = Validator.validateInputString("Mật khẩu: ", sc, new StringRule(1, 255));
    }
}
