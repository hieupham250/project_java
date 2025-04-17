package ra.edu.utils.pagination;

import ra.edu.business.model.Course;
import ra.edu.utils.TableUtils;
import ra.edu.validate.Validator;

public class PaginationUtils {
    public static <T> boolean paginate(PaginationConfig<T> config) {
        if (config.items == null || config.items.isEmpty()) {
            System.out.println("\u001B[31mKhông có dữ liệu để hiển thị!\u001B[0m");
            return false;
        }
        int totalItems = config.items.size();
        int totalPages = (int) Math.ceil((double) totalItems / config.pageSize);
        int page = Math.max(1, Math.min(config.page, totalPages));

        boolean continuePagination = true;
        while (continuePagination) {
            System.out.printf("Danh sách (Trang %d / %d):\n", page, totalPages);
            // Lấy tên lớp của đối tượng đầu tiên trong danh sách để quyết định hiển thị header
            String className = config.items.getFirst().getClass().getSimpleName();
            // Kiểm tra và hiển thị header tùy theo loại đối tượng
            switch (className) {
                case "Course":
                    TableUtils.printCourseHeader();  // Hiển thị header cho Course
                    break;
                case "Student":
                    TableUtils.printStudentHeader();  // Hiển thị header cho Student
                    break;
                default:
                    System.out.println("\u001B[31mLoại đối tượng không hợp lệ!\u001B[0m");
                    return false;
            }

            int fromIndex = (page - 1) * config.pageSize;
            int toIndex = Math.min(fromIndex + config.pageSize, totalItems);

            // In từng đối tượng trong danh sách
            config.items.subList(fromIndex, toIndex).forEach(item -> {
                switch (className) {
                    case "Course":
                        TableUtils.printCourse((Course) item);  // In thông tin Course
                        break;
                    case "Student":
//                        DisplayUtils.printStudent((Student) item);  // In thông tin Student
                        break;
                }
            });

            System.out.println("========= Lựa chọn =========");
            System.out.println("1. Xem trang trước");
            System.out.println("2. Xem trang tiếp theo");
            System.out.println("3. Quay lại menu");
            int choice = Validator.validateInputInteger("Nhập lựa chọn: ", config.sc);
            switch (choice) {
                case 1:
                    if (page > 1) {
                        page--;
                    } else {
                        System.out.println("\u001B[31mĐây là trang đầu tiên!\u001B[0m");
                    }
                    break;
                case 2:
                    if (page < totalPages) {
                        page++;
                    } else {
                        System.out.println("\u001B[31mKhông còn trang tiếp theo!\u001B[0m");
                    }
                    break;
                case 3:
                    continuePagination = false;
                    break;
                default:
                    System.out.println("\u001B[31mLựa chọn không hợp lệ!\u001B[0m");
                    break;
            }
        }
        return true;
    }

}
