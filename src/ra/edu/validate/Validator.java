package ra.edu.validate;

import java.util.Scanner;

public class Validator {
    public static int validateInputInteger(String message, Scanner sc) {
        System.out.print(message);
        do {
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("\u001B[31mDữ liệu không hợp lệ. Vui lòng nhập lại!\u001B[0m");
            } catch (Exception e) {
                System.out.println("\u001B[31mCó lỗi trong quá trình nhập. Vui lòng nhập lại!\u001B[0m");
            }
        } while(true);
    }

    public static String validateInputString(String message, Scanner sc, StringRule stringRule) {
        System.out.print(message);
        do {
            String inputString = sc.nextLine();
            try {
                if (stringRule.isValidString(inputString)) {
                    return inputString;
                } else {
                    System.out.println("\u001B[31mDữ liệu không hợp lệ. Độ dài chuỗi phải từ " + stringRule.getMinLength() + " đến " + stringRule.getMaxLength() + " ký tự. Vui lòng nhập lại!\u001B[0m");
                }
            } catch (Exception e) {
                System.out.println("\u001B[31mCó lỗi trong quá trình nhập. Vui lòng nhập lại!\u001B[0m");
            }
        } while(true);
    }
}
