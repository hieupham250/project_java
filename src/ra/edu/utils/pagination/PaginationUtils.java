package ra.edu.utils.pagination;

import ra.edu.business.service.course.CourseService;
import ra.edu.business.service.course.CourseServiceImp;
import ra.edu.validate.Validator;

import java.util.List;

public class PaginationUtils {
    public static <T> void paginate(PaginationConfig<T> config, ListProvider<T> provider) {
        CourseService courseService = new CourseServiceImp();
        if (config.getTotalItems() == 0) {
            System.out.println("\u001B[31mKhông có dữ liệu để hiển thị!\u001B[0m");
            return;
        }
        int totalPages = (int) Math.ceil((double) config.getTotalItems() / (double) config.getPageSize());
        int currentPage = 1;
        List<T> currentPageData = provider.getDataByPage(currentPage, config.getPageSize());
        do {
            System.out.printf("Danh sách (Trang %d / %d):\n", currentPage, totalPages);
            config.getPrintHeader().run();
            if (currentPageData != null) {
                currentPageData.forEach(config.getPrintItem());
            }
            for (int i = 1; i <= totalPages; i++) {
                if (i == currentPage) {
                    System.out.print("\u001B[34m[Trang " + i + "]\u001B[0m");
                } else {
                    System.out.print(" Trang " + i);
                }
            }
            System.out.println();
            System.out.println("========= Lựa chọn =========");
            System.out.println("1. Xem trang trước");
            System.out.println("2. Xem trang tiếp theo");
            System.out.println("3. Quay lại menu");
            int choice = Validator.validateInputInteger("Nhập lựa chọn: ", config.sc);
            switch (choice) {
                case 1:
                    if (currentPage > 1) {
                        currentPage--;
                        currentPageData = provider.getDataByPage(currentPage, config.getPageSize());
                    } else {
                        System.out.println("\u001B[31mĐây là trang đầu tiên!\u001B[0m");
                    }
                    break;
                case 2:
                    if (currentPage < totalPages) {
                        currentPage++;
                        currentPageData = provider.getDataByPage(currentPage, config.getPageSize());
                    } else {
                        System.out.println("\u001B[31mKhông còn trang tiếp theo!\u001B[0m");
                    }
                    break;
                case 3:
                    return;
                default:
                    System.out.println("\u001B[31mLựa chọn không hợp lệ!\u001B[0m");
                    break;
            }
        } while (true);
    }
}