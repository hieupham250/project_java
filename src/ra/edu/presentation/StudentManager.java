package ra.edu.presentation;

import ra.edu.business.model.Student;
import ra.edu.business.service.student.StudentService;
import ra.edu.business.service.student.StudentServiceImp;
import ra.edu.datatype.PaginationOption;
import ra.edu.datatype.StatusAccount;
import ra.edu.utils.PaginationUtils;
import ra.edu.utils.TableUtils;
import ra.edu.validate.StringRule;
import ra.edu.validate.StudentValidator;
import ra.edu.validate.Validator;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class StudentManager {
    public static StudentService studentService = new StudentServiceImp();

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
                    searchStudentByName(sc);
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

    public static void displayStudents(Scanner sc) {
        PaginationUtils<StudentService> paginator = new PaginationUtils<>(
                "",
                PaginationOption.LIST_ALL,
                studentService
        );
        paginator.paginate(sc);
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

    public static void updateStudent(Scanner sc) {
        List<Student> students = studentService.findAll();
        if (students.isEmpty()) {
            System.out.println("\u001B[31mHiện không có học viên nào để cập nhật!\u001B[0m");
            return;
        }
        int id = Validator.validateInputInteger("Nhập mã học viên muốn cập nhật", sc);
        Student existingStudent = studentService.getStudentById(id);
        if (existingStudent == null) {
            System.out.println("\u001B[31mMã học viên không tồn tại!\u001B[0m");
            return;
        }
        System.out.println("Thông tin học viên hiện tại:");
        TableUtils.printStudentTable(existingStudent);
        while (true) {
            System.out.println("========= Chọn thông tin muốn cập nhật =========");
            System.out.println("1. Tên học viên");
            System.out.println("2. Ngày sinh");
            System.out.println("3. Giới tính");
            System.out.println("4. Số điện thoại");
            System.out.println("5. Trạng thái");
            System.out.println("6. Quay lại menu chính");
            int choice = Validator.validateInputInteger("Nhập lựa chọn: ", sc);
            switch (choice) {
                case 1:
                    String newName = Validator.validateInputString("Nhập tên học viên mới: ", sc, new StringRule(15, "Tên không được để trống!"));
                    existingStudent.setName(newName);
                    if (studentService.update(existingStudent)) {
                        System.out.println("\u001B[32mTên học viên đã được cập nhật thành công!\u001B[0m");
                    } else {
                        System.out.println("\u001B[31mLỗi khi cập nhật tên học viên!\u001B[0m");
                    }
                    break;
                case 2:
                    LocalDate dobNew = Validator.validateDate("Nhập ngày sinh mới: ", sc);
                    existingStudent.setDob(dobNew);
                    if (studentService.update(existingStudent)) {
                        System.out.println("\u001B[32mTên học viên đã được cập nhật thành công!\u001B[0m");
                    } else {
                        System.out.println("\u001B[31mLỗi khi cập nhật tên học viên!\u001B[0m");
                    }
                    break;
                case 3:
                    boolean sexNew = StudentValidator.validateSex("Nhập giới tính (Nam/Nữ): ", sc);
                    existingStudent.setSex(sexNew);
                    if (studentService.update(existingStudent)) {
                        System.out.println("\u001B[32mTên học viên đã được cập nhật thành công!\u001B[0m");
                    } else {
                        System.out.println("\u001B[31mLỗi khi cập nhật tên học viên!\u001B[0m");
                    }
                    break;
                case 4:
                    while (true) {
                        String phone = Validator.validateInputString("Nhập số điện thoại: ", sc, new StringRule(15, "Số điện thoại không được để trống!"));
                        if (Validator.isValidatePhone(phone)) {
                            existingStudent.setPhone(phone);
                            break;
                        } else {
                            System.out.println("\u001B[31mSố điện thoại không hợp lệ. Vui lòng nhập lại!\u001B[0m");
                        }
                    }
                    if (studentService.update(existingStudent)) {
                        System.out.println("\u001B[32mTên học viên đã được cập nhật thành công!\u001B[0m");
                    } else {
                        System.out.println("\u001B[31mLỗi khi cập nhật tên học viên!\u001B[0m");
                    }
                    break;
                case 5:
                    System.out.println("========= Cập nhật trạng thái học viên =========");
                    System.out.println("1. Hoạt động (ACTIVE)");
                    System.out.println("2. Không hoạt động (INACTIVE)");
                    System.out.println("3. Bị chặn (BLOCKED)");
                    int statusChoice = Validator.validateInputInteger("Nhập lựa chọn: ", sc);
                    switch (statusChoice) {
                        case 1:
                            existingStudent.setStatus(StatusAccount.ACTIVE);
                            break;
                        case 2:
                            existingStudent.setStatus(StatusAccount.INACTIVE);
                            break;
                        case 3:
                            existingStudent.setStatus(StatusAccount.BLOCKED);
                            break;
                        default:
                            System.out.println("\u001B[31mLựa chọn không hợp lệ!\u001B[0m");
                            break;
                    }
                    if (studentService.update(existingStudent)) {
                        System.out.println("\u001B[32mTrạng thái học viên đã được cập nhật thành công!\u001B[0m");
                    } else {
                        System.out.println("\u001B[31mLỗi khi cập nhật trạng thái học viên!\u001B[0m");
                    }
                    break;
                case 6:
                    return;
                default:
                    System.out.println("\u001B[31mLựa chọn không hợp lệ!\u001B[0m");
            }
        }
    }

    public static void deleteStudent(Scanner sc) {
        List<Student> students = studentService.findAll();
        if (students.isEmpty()) {
            System.out.println("\u001B[31mHiện không có học viên nào để xóa!\u001B[0m");
            return;
        }
        int id = Validator.validateInputInteger("Nhập mã học viên muốn xóa: ", sc);
        Student existingStudent = studentService.getStudentById(id);
        if (existingStudent == null) {
            System.out.println("\u001B[31mMã học viên không tồn tại!\u001B[0m");
            return;
        }
        System.out.println("Thông tin học viên muốn xóa:");
        TableUtils.printStudentTable(existingStudent);
        System.out.println("Bạn có chắc chắn muốn xóa học viên này?");
        while (true) {
            System.out.println("1. xác nhận");
            System.out.println("2. Hủy");
            int choice = Validator.validateInputInteger("Nhập lựa chọn: ", sc);
            switch (choice) {
                case 1:
                    if (studentService.checkStudentHasCourses(id)) {
                        System.out.println("\u001B[31mKhông thể xóa học viên này vì đã tham gia một khóa học!\u001B[0m");
                        return;
                    }
                    if (studentService.delete(existingStudent)) {
                        System.out.println("\u001B[32mHọc viên đã được xóa thành công!\u001B[0m");
                    } else {
                        System.out.println("\u001B[31mLỗi khi xóa học viên!\u001B[0m");
                    }
                    return;
                case 2:
                    return;
                default:
                    System.out.println("\u001B[31mLựa chọn không hợp lệ!\u001B[0m");
            }
        }
    }

    public static void searchStudentByName(Scanner sc) {
        String keyword = Validator.validateInputString("Nhập tên học viên muốn tìm kiếm: ", sc, new StringRule(100, "Dữ liệu không được để trống!"));
        PaginationUtils<StudentService> paginator = new PaginationUtils<>(
                keyword,
                PaginationOption.SEARCH,
                studentService
        );
        paginator.paginate(sc);
    }

    private static String getSortOption(Scanner sc) {
        String type = null;
        do {
            System.out.println("========= Chọn cách sắp xếp =========");
            System.out.println("1. Sắp xếp theo tên");
            System.out.println("2. Sắp xếp theo ID");
            System.out.println("3. Quay lại menu chính");
            int choice = Validator.validateInputInteger("Nhập lựa chọn: ", sc);
            switch (choice) {
                case 1:
                    type = "name";
                    break;
                case 2:
                    type = "id";
                    break;
                case 3:
                    return null;
                default:
                    System.out.println("\u001B[31mLựa chọn không hợp lệ!\u001B[0m");
            }
        } while (type == null);
        do {
            System.out.println("1. Tăng dần");
            System.out.println("2. Giảm dần");
            System.out.println("3. Thoát");
            int subChoice = Validator.validateInputInteger("Nhập lựa chọn: ", sc);
            switch (subChoice) {
                case 1:
                    return type + "_asc";
                case 2:
                    return type + "_desc";
                case 3:
                    return null;
                default:
                    System.out.println("\u001B[31mLựa chọn không hợp lệ!\u001B[0m");
            }
        } while (true);
    }

    public static void sortStudents(Scanner sc) {
        String sortOption = getSortOption(sc);
        if (sortOption == null) return;
        PaginationUtils<StudentService> paginator = new PaginationUtils<>(
                sortOption,
                PaginationOption.SORT,
                studentService
        );
        paginator.paginate(sc);
    }
}
