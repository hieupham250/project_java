package ra.edu.presentation;

import ra.edu.business.model.Course;
import ra.edu.business.service.course.CourseService;
import ra.edu.business.service.course.CourseServiceImp;
import ra.edu.datatype.PaginationOption;
import ra.edu.utils.PaginationUtils;
import ra.edu.utils.TableUtils;
import ra.edu.validate.CourseValidator;
import ra.edu.validate.StringRule;
import ra.edu.validate.Validator;

import java.util.List;
import java.util.Scanner;

public class CourseManager {
    public static CourseService courseService = new CourseServiceImp();

    public static void menuCourseManager(Scanner sc) {
        do {
            System.out.println("==================================");
            System.out.println(" 1. Hiển thị danh sách khóa học");
            System.out.println(" 2. Thêm mới khóa học");
            System.out.println(" 3. Chỉnh sửa thông tin khóa học");
            System.out.println(" 4. Xóa khóa học");
            System.out.println(" 5. Tìm kiếm theo tên");
            System.out.println(" 6. Sắp xếp");
            System.out.println(" 7. quay về menu chính");
            System.out.println("==================================");
            int choice = Validator.validateInputInteger("Nhập lựa chọn: ", sc);
            switch (choice) {
                case 1:
                    displayCourses(sc);
                    break;
                case 2:
                    createCourse(sc);
                    break;
                case 3:
                    updateCourse(sc);
                    break;
                case 4:
                    deleteCourse(sc);
                    break;
                case 5:
                    searchCourseByName(sc);
                    break;
                case 6:
                    sortCourses(sc);
                    break;
                case 7:
                    return;
                default:
                    System.out.println("\u001B[31mLựa chọn không hợp lệ!\u001B[0m");
            }
        } while (true);
    }

    public static void displayCourses(Scanner sc) {
        PaginationUtils<CourseService> paginator  = new PaginationUtils<>(
                "",
                PaginationOption.LIST_ALL,
                courseService
        );
        paginator.paginate(sc);
    }

    public static void createCourse(Scanner sc) {
        Course course = new Course();
        course.inputData(sc, courseService);
        if (courseService.create(course)) {
            System.out.println("\u001B[32mThêm khóa học thành công!\u001B[0m");
        } else {
            System.out.println("\u001B[31mLỗi khi thêm khóa học!\u001B[0m");
        }
    }

    public static void updateCourse(Scanner sc) {
        List<Course> courses = courseService.findAll();
        if (courses.isEmpty()) {
            System.out.println("\u001B[31mHiện không có khóa học nào để cập nhật!\u001B[0m");
            return;
        }
        int id = Validator.validateInputInteger("Nhập mã khóa học muốn cập nhật: ", sc);
        Course existingCourse = courseService.getCourseById(id);
        if (existingCourse == null) {
            System.out.println("\u001B[31mMã khóa học không tồn tại!\u001B[0m");
            return;
        }
        System.out.println("Thông tin khóa học hiện tại:");
        TableUtils.printCourseTable(existingCourse);
        while (true) {
            System.out.println("========= Chọn thông tin muốn cập nhật =========");
            System.out.println("1. Tên khóa học");
            System.out.println("2. Thời lượng (giờ)");
            System.out.println("3. Giảng viên phụ trách");
            System.out.println("4. Quay lại menu chính");
            int choice = Validator.validateInputInteger("Nhập lựa chọn: ", sc);
            switch (choice) {
                case 1:
//                    String newName = Validator.validateInputString("Nhập tên khóa học mới: ", sc, new StringRule(100, "Tên khóa học không được để trống!"));
                    String newName;
                    while (true) {
                        newName = Validator.validateInputString("Nhập tên khóa học mới: ", sc, new StringRule(100, "Tên khóa học không được để trống!"));
                        if (!newName.equalsIgnoreCase(existingCourse.getName()) && CourseValidator.isNameExisted(newName, courseService)) {
                            System.out.println("\u001B[31mTên khóa học đã tồn tại. Vui lòng nhập tên khác!\u001B[0m");
                        } else {
                            break;
                        }
                    }
                    existingCourse.setName(newName);
                    if (courseService.update(existingCourse)) {
                        System.out.println("\u001B[32mTên khóa học đã được cập nhật thành công!\u001B[0m");
                    } else {
                        System.out.println("\u001B[31mLỗi khi cập nhật tên khóa học!\u001B[0m");
                    }
                    break;
                case 2:
                    int newDuration = Validator.validateInputInteger("Nhập thời lượng mới (giờ): ", sc);
                    existingCourse.setDuration(newDuration);
                    if (courseService.update(existingCourse)) {
                        System.out.println("\u001B[32mThời lượng khóa học đã được cập nhật thành công!\u001B[0m");
                    } else {
                        System.out.println("\u001B[31mLỗi khi cập nhật thời lượng khóa học!\u001B[0m");
                    }
                    break;
                case 3:
                    String newInstructor = Validator.validateInputString("Nhập giảng viên mới: ", sc, new StringRule(100, "Tên giảng viên không được để trống!"));
                    existingCourse.setInstructor(newInstructor);
                    if (courseService.update(existingCourse)) {
                        System.out.println("\u001B[32mGiảng viên đã được cập nhật thành công!\u001B[0m");
                    } else {
                        System.out.println("\u001B[31mLỗi khi cập nhật giảng viên!\u001B[0m");
                    }
                    break;
                case 4:
                    return;
                default:
                    System.out.println("\u001B[31mLựa chọn không hợp lệ!\u001B[0m");
            }
        }
    }

    public static void deleteCourse(Scanner sc) {
        List<Course> courses = courseService.findAll();
        if (courses.isEmpty()) {
            System.out.println("\u001B[31mHiện không có khóa học nào để xóa!\u001B[0m");
            return;
        }
        int id = Validator.validateInputInteger("Nhập mã khóa học muốn xóa: ", sc);
        Course existingCourse = courseService.getCourseById(id);
        if (existingCourse == null) {
            System.out.println("\u001B[31mMã khóa học không tồn tại!\u001B[0m");
            return;
        }
        System.out.println("Thông tin khóa học muốn xóa:");
        TableUtils.printCourseTable(existingCourse);
        System.out.println("Bạn có chắc chắn muốn xóa khóa học này?");
        while (true) {
            System.out.println("1. xác nhận");
            System.out.println("2. Hủy");
            int choice = Validator.validateInputInteger("Nhập lựa chọn: ", sc);
            switch (choice) {
                case 1:
                    if (courseService.delete(existingCourse)) {
                        System.out.println("\u001B[32mKhóa học đã được xóa thành công!\u001B[0m");
                    } else {
                        System.out.println("\u001B[31mKhông thể xóa khóa học vì đã có sinh viên tham gia!\u001B[0m");
                    }
                    return;
                case 2:
                    return;
                default:
                    System.out.println("\u001B[31mLựa chọn không hợp lệ!\u001B[0m");
            }
        }
    }

    public static void searchCourseByName(Scanner sc) {
        String keyword = Validator.validateInputString("Nhập tên khóa học muốn tìm kiếm: ", sc, new StringRule(100, "Dữ liệu không được để trống!"));
        PaginationUtils<CourseService> paginator  = new PaginationUtils<>(
                keyword,
                PaginationOption.SEARCH,
                courseService
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


    public static void sortCourses(Scanner sc) {
        String sortOption = getSortOption(sc);
        if (sortOption == null) return;
        PaginationUtils<CourseService> paginator  = new PaginationUtils<>(
                sortOption,
                PaginationOption.SORT,
                courseService
        );
        paginator.paginate(sc);
    }
}
