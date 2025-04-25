package ra.edu.presentation;

import ra.edu.business.service.statistic.StatisticService;
import ra.edu.business.service.statistic.StatisticServiceImp;
import ra.edu.business.model.Statistic;
import ra.edu.datatype.PaginationOption;
import ra.edu.utils.PaginationUtils;
import ra.edu.utils.TableUtils;
import ra.edu.validate.Validator;

import java.util.List;
import java.util.Scanner;

public class StatisticUI {
    public static StatisticService statisticService = new StatisticServiceImp();

    public static void displayStatisticMenu(Scanner sc) {
        do {
            System.out.println("=================================");
            System.out.println(" 1. Thống kê số lượng khóa học và học viên");
            System.out.println(" 2. Thống kê sinh viên theo từng khóa học");
            System.out.println(" 3. Top 5 khóa học đông học viên nhất");
            System.out.println(" 4. Liệt kê khóa học có trên 10 học viên");
            System.out.println(" 5. Quay về menu chính");
            System.out.println("==================================");
            int choice = Validator.validateInputInteger("Nhập lựa chọn: ", sc);
            switch (choice) {
                case 1:
                    showTotalCoursesAndStudents();
                    break;
                case 2:
                    showStudentsCountByCourse(sc);
                    break;
                case 3:
                    showTop5MostPopularCourses();
                    break;
                case 4:
                    showCoursesWithMoreThan10Students(sc);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("\u001B[31mLựa chọn không hợp lệ!\u001B[0m");
            }
        } while (true);
    }

    private static void showTotalCoursesAndStudents() {
        Statistic statistic = statisticService.getTotalCoursesAndStudents();
        System.out.println("=================================");
        System.out.println("Tổng số khóa học: " + statistic.getTotalCourses());
        System.out.println("Tổng số học viên: " + statistic.getTotalStudents());
        System.out.println("=================================");
    }

    private static void showStudentsCountByCourse(Scanner sc) {
        PaginationUtils<StatisticService> paginator  = new PaginationUtils<>(
                "",
                PaginationOption.LIST_ALL,
                statisticService
        );
        paginator.paginate(sc);
    }

    private static void showTop5MostPopularCourses() {
        List<Statistic> statistics = statisticService.getTop5MostPopularCourses();
        TableUtils.printCourseStatisticTable(statistics);
    }

    private static void showCoursesWithMoreThan10Students(Scanner sc) {
        PaginationUtils<StatisticService> paginator  = new PaginationUtils<>(
                "",
                PaginationOption.COURSE_MORE_THAN_10_STUDENTS,
                statisticService
        );
        paginator.paginate(sc);
    }
}
