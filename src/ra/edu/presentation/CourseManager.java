package ra.edu.presentation;

import ra.edu.business.model.Course;
import ra.edu.business.service.course.CourseService;
import ra.edu.business.service.course.CourseServiceImp;
import ra.edu.utils.TableUtils;
import ra.edu.validate.CourseValidator;
import ra.edu.validate.StringRule;
import ra.edu.validate.Validator;

import java.util.List;
import java.util.Scanner;

public class CourseManager {
    public static CourseService courseService = new CourseServiceImp();
    private static final int totalItems = 5;

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

    public static void paginate(List<Course> courses, Scanner sc) {
        int size = courses.size();
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
            TableUtils.printCoursesTable(courses.subList(start, end));
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

    public static void displayCourses(Scanner sc) {
        List<Course> courses = courseService.findAll();
        paginate(courses, sc);
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
        int id = Validator.validateInputInteger("Nhập mã khóa học muốn cập nhât: ", sc);
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
                    String newName = Validator.validateInputString("Nhập tên khóa học mới: ", sc, new StringRule(100, "Tên khóa học không được để trống!"));
                    if (CourseValidator.isNameExisted(newName, courseService)) {
                        System.out.println("\u001B[31mTên khóa học đã tồn tại. Vui lòng nhập tên khác!\u001B[0m");
                    } else {
                        existingCourse.setName(newName);
                        if (courseService.update(existingCourse)) {
                            System.out.println("\u001B[32mTên khóa học đã được cập nhật thành công!\u001B[0m");
                        } else {
                            System.out.println("\u001B[31mLỗi khi cập nhật tên khóa học!\u001B[0m");
                        }
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
            System.out.println("\u001B[31mHiện không có khóa học nào để cập nhật!\u001B[0m");
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
                    if (courseService.checkCourseHasStudents(id)) {
                        System.out.println("\u001B[31mKhông thể xóa khóa học vì đã có sinh viên tham gia!\u001B[0m");
                        return;
                    }
                    if (courseService.delete(existingCourse)) {
                        System.out.println("\u001B[32mKhóa học đã được xóa thành công!\u001B[0m");
                    } else {
                        System.out.println("\u001B[31mLỗi khi xóa khóa học!\u001B[0m");
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
        String name = Validator.validateInputString("Nhập tên khóa học muốn tìm kiếm: ", sc, new StringRule(100, "Dữ liệu không được để trống!"));
        System.out.println("Tìm kiếm khóa học với từ khóa: " + name);
        List<Course> courses = courseService.searchCoursesByName(name);
        if (courses.isEmpty()) {
            System.out.println("\u001B[31mKhông tìm thấy khóa học nào với từ khóa " + name + "!\u001B[0m");
            return;
        }
        paginate(courses, sc);
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

        List<Course> sortedCourses = courseService.getCoursesSorted(sortOption);
        if (sortedCourses.isEmpty()) {
            System.out.println("\u001B[31mKhông có khóa học nào để hiển thị!\u001B[0m");
            return;
        }
        paginate(sortedCourses, sc);
    }
}
