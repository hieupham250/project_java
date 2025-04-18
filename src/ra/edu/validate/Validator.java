package ra.edu.validate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Validator {
    public static int validateInputInteger(String message, Scanner sc) {
        do {
            try {
                System.out.print(message);
                int input = Integer.parseInt(sc.nextLine());
                if (input > 0) {
                    return input;
                }
                System.out.println("\u001B[31mDữ liệu phải lớn hơn 0. Vui lòng nhập lại\u001B[0m");
            } catch (NumberFormatException e) {
                System.out.println("\u001B[31mDữ liệu không hợp lệ, phải là số nguyên. Vui lòng nhập lại!\u001B[0m");
            } catch (Exception e) {
                System.out.println("\u001B[31mCó lỗi trong quá trình nhập. Vui lòng nhập lại!\u001B[0m");
            }
        } while(true);
    }

    public static String validateInputString(String message, Scanner sc, StringRule stringRule) {
        do {
            try {
                System.out.print(message);
                String input = sc.nextLine().trim().replaceAll("\\s+", " ");
                if (input.isEmpty()) {
                    System.out.println("\u001B[31m"+ stringRule.getMessageError() + "\u001B[0m");
                    continue;
                }
                if (!stringRule.isValidString(input)) {
                    System.out.println("\u001B[31mDữ liệu vượt quá độ dài cho phép. Vui lòng nhập lại!\u001B[0m");
                    continue;
                }
                return input;
            } catch (Exception e) {
                System.out.println("\u001B[31mCó lỗi trong quá trình nhập. Vui lòng nhập lại!\u001B[0m");
            }
        } while(true);
    }

    public static boolean isValidateEmail (String value) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return value != null && Pattern.matches(emailRegex, value.trim());
    }

    public static boolean isValidatePhone (String value) {
        String phoneRegex = "^(03[2-9]|05[6|8|9]|07[0|6-9]|08[1-9]|09[0-9])\\d{7}$";
        return value != null && Pattern.matches(phoneRegex, value.trim());
    }

    public static LocalDate validateDate(String message, Scanner sc) {
        System.out.print(message);
        do {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            try {
                return LocalDate.parse(sc.nextLine(), dtf);
            } catch (DateTimeParseException e) {
                System.out.println("\u001B[31mĐịnh dạng ngày tháng không đúng, vui lòng nhập lại\u001B[0m");
            } catch (Exception e) {
                System.out.println("\u001B[31mCó lỗi trong quá trình nhập dữ liệu, vui lòng nhập lại\u001B[0m");
            }
        } while (true);
    }
}
