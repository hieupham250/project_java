package ra.edu.presentation;

import ra.edu.business.model.Student;
import ra.edu.business.service.student.StudentService;
import ra.edu.business.service.student.StudentServiceImp;
import ra.edu.utils.TableUtils;
import ra.edu.validate.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentManager {
    public static StudentService studentService = new StudentServiceImp();
    private static final int totalItems = 5;
    public static void menuStudentManager(Scanner sc) {
        do {
            System.out.println("=================================");
            System.out.println(" 1. Hiển thị danh sách học viên");
            System.out.println(" 2. Thêm mới học viên");
            System.out.println(" 3. Chỉnh sửa thông tin học viên");
            System.out.println(" 4. Xóa học viên");
            System.out.println(" 5. Tìm kiếm");
            System.out.println(" 6. Sắp xếp");
            System.out.println(" 7. Quay về menu chính");
            System.out.println("==================================");
            int choice = Validator.validateInputInteger("Nhập lựa chọn: ", sc);
            switch (choice) {
                case 1:
                    displayStudents(sc);
                    break;
                case 2:
                    createStudent(sc);
                    break;
                case 3:
                    updateStudent(sc);
                    break;
                case 4:
                    deleteStudent(sc);
                    break;
                case 5:
                    searchStudent(sc);
                    break;
                case 6:
                    sortStudents(sc);
                    break;
                case 7:
                    return;
                default:
                    System.out.println("\u001B[31mLựa chọn không hợp lệ!\u001B[0m");
            }
        } while (true);
    }

    public static void paginate(List<Student> students, Scanner sc) {
        int size = students.size();
        if (size == 0) {
            System.out.println("\u001B[31mKhông có dữ liệu để hiển thị!\u001B[0m");
            return;
        }
        int currentPage = 1;
        int totalPages = (int) Math.ceil((double) size / totalItems);
        do {
            int start = (currentPage - 1) * totalItems;
            int end = Math.min(start + totalItems, size);
            System.out.printf("Danh sách (Trang %d / %d):\n", currentPage, totalPages);
            TableUtils.printStudentsTable(students.subList(start, end));
            System.out.println("========= Lựa chọn =========");
            System.out.println("1. Xem trang trước");
            System.out.println("2. Xem trang tiếp theo");
            System.out.println("3. Quay lại menu");
            int choice = Validator.validateInputInteger("Nhập lựa chọn: ", sc);
            switch (choice) {
                case 1:
                    if (currentPage > 1) {
                        currentPage--;
                    } else {
                        System.out.println("\u001B[31mĐây là trang đầu tiên!\u001B[0m");
                    }
                    break;
                case 2:
                    if (currentPage < totalPages) {
                        currentPage++;
                    } else {
                        System.out.println("\u001B[31mĐã là trang cuối cùng!\u001B[0m");
                    }
                    break;
                case 3:
                    return;
                default:
                    System.out.println("\u001B[31mLựa chọn không hợp lệ!\u001B[0m");
            }
        } while (true);
    }

    public static void displayStudents(Scanner sc) {
        List<Student> students = studentService.findAll();
        paginate(students, sc);
    }

    public static void createStudent(Scanner sc) {
        Student student = new Student();
        student.inputData(sc, studentService);
        if (studentService.create(student)) {
            System.out.println("\u001B[32mThêm học viên thành công!\u001B[0m");
        } else {
            System.out.println("\u001B[31mLỗi khi thêm học viên!\u001B[0m");
        }
    }

    public static void updateStudent(Scanner sc) {}

    public static void deleteStudent(Scanner sc) {}

    public static void searchStudent(Scanner sc) {}

    public static void sortStudents(Scanner sc) {}
}
