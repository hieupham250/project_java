package ra.edu.utils;

import ra.edu.business.model.Course;
import ra.edu.business.model.Student;
import ra.edu.business.service.course.CourseService;
import ra.edu.business.service.student.StudentService;
import ra.edu.datatype.PaginationOption;
import ra.edu.validate.Validator;
import java.util.List;
import java.util.Scanner;

public class PaginationUtils<T> {
    private static final int PAGE_SIZE = 5;
    private int size;
    private String value;
    private PaginationOption option;
    private T service;

    public PaginationUtils(String value, PaginationOption option, T service) {
        this.value = value;
        this.option = option;
        this.service = service;
    }

    public void paginate(Scanner sc) {
        int currentPage = 1;
        List<?> data = null;
        int totalPages = 0;
        do {
            if (service instanceof CourseService) {
                CourseService courseService = (CourseService) service;
                int totalCourses;
                switch (option) {
                    case LIST_ALL:
                        totalCourses = courseService.findAll().size();
                        totalPages = (int) Math.ceil((double) totalCourses / PAGE_SIZE);
                        data = courseService.getCoursesByPage(currentPage, PAGE_SIZE);
                        break;
                    case SEARCH:
                        int[] totalRecordsOut = new int[1];
                        data = courseService.searchCoursesByName(value, currentPage, PAGE_SIZE, totalRecordsOut);
                        size = totalRecordsOut[0];
                        totalPages = (int) Math.ceil((double) this.size / PAGE_SIZE);
                        break;
                        case SORT:
                            totalCourses = courseService.findAll().size();
                            totalPages = (int) Math.ceil((double) totalCourses / PAGE_SIZE);
                            data = courseService.getCoursesSorted(value, currentPage, PAGE_SIZE);
                            break;
                    default:
                        System.out.println("\u001B[31mTùy chọn phân trang không hợp lệ!\u001B[0m");
                        return;
                }
            } else if (service instanceof StudentService) {
                StudentService studentService = (StudentService) service;
                int totalStudents;
                switch (option) {
                    case LIST_ALL:
                        totalStudents = studentService.findAll().size();
                        totalPages = (int) Math.ceil((double) totalStudents / PAGE_SIZE);
                        data = studentService.getStudentsByPage(currentPage, PAGE_SIZE);
                        break;
                    case SEARCH:
                        int[] totalRecordsOut = new int[1];
                        data = studentService.searchStudents(value, currentPage, PAGE_SIZE, totalRecordsOut);
                        size = totalRecordsOut[0];
                        totalPages = (int) Math.ceil((double) this.size / PAGE_SIZE);
                        break;
                    case SORT:
                        totalStudents = studentService.findAll().size();
                        totalPages = (int) Math.ceil((double) totalStudents / PAGE_SIZE);
                        data = studentService.getStudentsSorted(value, currentPage, PAGE_SIZE);
                        break;
                    default:
                        System.out.println("\u001B[31mTùy chọn phân trang không hợp lệ!\u001B[0m");
                        return;
                }
            } else {
                System.out.println("\u001B[31mLoại dịch vụ không được hỗ trợ!\u001B[0m");
                return;
            }
            if (data == null || data.isEmpty()) {
                System.out.println("\u001B[31mKhông có dữ liệu để hiển thị!\u001B[0m");
                return;
            }
            System.out.printf("Danh sách (Trang %d / %d):\n", currentPage, totalPages);
            if (service instanceof CourseService) {
                TableUtils.printCoursesTable((List<Course>) data);
            } else if (service instanceof StudentService) {
                TableUtils.printStudentsTable((List<Student>) data);
            }
            for (int i = 1; i <= totalPages; i++) {
                if (i == currentPage) {
                    System.out.print("\u001B[34mTrang " + i + "\u001B[0m ");
                } else {
                    System.out.print("Trang " + i + " ");
                }
            }
            System.out.println();
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
}
