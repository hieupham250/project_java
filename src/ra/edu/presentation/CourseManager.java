package ra.edu.presentation;

import ra.edu.validate.Validator;

import java.util.Scanner;

public class CourseManager {
    public static void menuCourseManager(Scanner sc) {
        do {
            System.out.println("1. Hiển thị danh sách khóa học");
            System.out.println("2. Thêm mới khóa học");
            System.out.println("3. Chỉnh sửa thông tin khóa học");
            System.out.println("4. Xóa khóa học");
            System.out.println("5. Tìm kiếm theo tên");
            System.out.println("6. Sắp xếp theo tên hoặc id");
            System.out.println("7. quay về menu chính");
            int choice = Validator.validateInputInteger("Nhập lựa chọn: ", sc);
            switch (choice) {
                case 1:
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
                    break;
                case 7:
                    return;
                default:
                    System.out.println("\u001B[31mLựa chọn không hợp lệ\u001B[0m");
            }
        } while (true);
    }
}
