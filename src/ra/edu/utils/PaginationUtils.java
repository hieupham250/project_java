package ra.edu.utils;

import ra.edu.business.model.*;
import ra.edu.business.service.course.CourseService;
import ra.edu.business.service.enrollment.EnrollmentService;
import ra.edu.business.service.statistic.StatisticService;
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
    private int id;

    // Constructor tìm kiếm/sắp xếp
    public PaginationUtils(String value, PaginationOption option, T service) {
        this.value = value;
        this.option = option;
        this.service = service;
    }

    public PaginationUtils(int id, PaginationOption option, T service) {
        this.id = id;
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
                    case COURSE_REGISTERED: // xem khóa học đã đăng ký của học viên đang đăng nhập
                        int[] totalRegisteredOut = new int[1];
                        data = studentService.getMyRegisteredCourses(id, currentPage, PAGE_SIZE, totalRegisteredOut);
                        size = totalRegisteredOut[0];
                        totalPages = (int) Math.ceil((double) this.size / PAGE_SIZE);
                        break;
                    default:
                        System.out.println("\u001B[31mTùy chọn phân trang không hợp lệ!\u001B[0m");
                        return;
                }
            } else if (service instanceof EnrollmentService) {
                EnrollmentService enrollmentService = (EnrollmentService) service;
                switch (option) {
                    case COURSE_STUDENT_REGISTERED: // xem tất cả học viên theo khóa
                        int[] totalCourseStudentsOut = new int[1];
                        data = enrollmentService.getRegisteredStudentsByCourse(id, currentPage, PAGE_SIZE, totalCourseStudentsOut);
                        size = totalCourseStudentsOut[0];
                        totalPages = (int) Math.ceil((double) this.size / PAGE_SIZE);
                        break;
                    default:
                        System.out.println("\u001B[31mTùy chọn phân trang không hợp lệ!\u001B[0m");
                        return;
                }
            } else if (service instanceof StatisticService) {
                StatisticService statisticService = (StatisticService) service;
                switch (option) {
                    case LIST_ALL:
                        int[] totalStudentCountOut = new int[1];
                        data = statisticService.getStudentsCountByCourse(currentPage, PAGE_SIZE, totalStudentCountOut);
                        int totalStudentCount = totalStudentCountOut[0];
                        totalPages = (int) Math.ceil((double) totalStudentCount / PAGE_SIZE);
                        break;
                        case COURSE_MORE_THAN_10_STUDENTS:
                            int[] totalCourseOver10Out = new int[1];
                            data = statisticService.getCoursesWithMoreThan10Students(currentPage, PAGE_SIZE, totalCourseOver10Out);
                            int totalCoursesOver10 = totalCourseOver10Out[0];
                            totalPages = (int) Math.ceil((double) totalCoursesOver10 / PAGE_SIZE);
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
                switch (option) {
                    case SEARCH:
                        System.out.println("\u001B[31mKhông tìm thấy kết quả phù hợp!\u001B[0m");
                        break;
                    case LIST_ALL:
                        System.out.println("\u001B[31mDanh sách hiện đang trống!\u001B[0m");
                        break;
                    case SORT:
                        System.out.println("\u001B[31mKhông có dữ liệu để sắp xếp!\u001B[0m");
                        break;
                    case COURSE_REGISTERED:
                        System.out.println("\u001B[31mBạn chưa đăng ký khóa học nào!\u001B[0m");
                        break;
                    case COURSE_STUDENT_REGISTERED:
                        System.out.println("\u001B[31mKhông có học viên nào đăng ký khóa học này!\u001B[0m");
                        break;
                    case COURSE_MORE_THAN_10_STUDENTS:
                        System.out.println("\u001B[31mKhông có khóa học nào có trên 10 học viên!\u001B[0m");
                        break;
                    default:
                        System.out.println("\u001B[31mKhông có dữ liệu để hiển thị!\u001B[0m");
                        break;
                }
                return;
            }

            System.out.printf("Danh sách (Trang %d / %d):\n", currentPage, totalPages);
            if (service instanceof CourseService) {
                TableUtils.printCoursesTable((List<Course>) data);
            } else if (service instanceof StudentService) {
                switch (option) {
                    case COURSE_REGISTERED:
                        TableUtils.printRegisteredCoursesTable((List<RegisteredCourseInfo>) data);
                        break;
                    default:
                        TableUtils.printStudentsTable((List<Student>) data);
                        break;
                }
            } else if (service instanceof EnrollmentService) {
                TableUtils.printAdminRegStudentTable((List<RegisteredStudentInfo>) data);
            } else if (service instanceof StatisticService) {
                TableUtils.printCourseStatisticTable((List<Statistic>) data);
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
