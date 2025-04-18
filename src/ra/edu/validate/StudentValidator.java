package ra.edu.validate;

import ra.edu.business.service.student.StudentService;

import java.util.Scanner;

public class StudentValidator {
    public static boolean isEmailExisted(String email, StudentService studentService) {
        return studentService.isAccountEmailExist(email);
    }

    public static boolean validateSex(String message, Scanner sc) {
        do {
            String input = Validator.validateInputString(message, sc, new StringRule(5, "Giới tính không được để trống!"));
            input = input.toLowerCase();
            if (input.equals("nam")) {
                return true;
            } else if (input.equals("nữ") || input.equals("nu")) {
                return false;
            } else {
                System.out.println("\u001B[31mGiới tính không hợp lệ. Vui lòng nhập 'Nam' hoặc 'Nữ'!\u001B[0m");
            }
        } while (true);
    }
}
