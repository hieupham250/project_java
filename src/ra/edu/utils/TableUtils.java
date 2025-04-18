package ra.edu.utils;

import ra.edu.business.model.Course;
import ra.edu.business.model.Student;

public class TableUtils {
    private static final String LINE = "+--------------+--------------------------------+------------+------------------------+--------------+";
    private static final String HEADER_COURSE = String.format(
            "| %-12s | %-30s | %-10s | %-22s | %-12s |",
            "Mã khóa học", "Tên khóa học", "Thời lượng", "Giảng viên", "Ngày tạo"
    );

    private static final String LINE_STUDENT = "+------------+-------------------------+------------+-------------------------+--------+-----------------+--------------+";
    private static final String HEADER_STUDENT = String.format(
            "| %-10s | %-23s | %-10s | %-23s | %-6s | %-15s | %-12s |",
            "Mã sinh viên", "Tên sinh viên", "Ngày sinh", "Email", "Giới tính", "Số điện thoại", "Ngày tạo"
    );

    public static void printCourseHeader() {
        System.out.println(LINE);
        System.out.println(HEADER_COURSE);
        System.out.println(LINE);
    }

    public static void printCourseRow(Course course) {
        System.out.printf("| %-12d | %-30s | %-10d | %-22s | %-12s |\n",
                course.getId(), course.getName(), course.getDuration(), course.getInstructor(), DateUtils.formatDate(course.getCreateAt()));
        System.out.println(LINE);
    }

    public static void printStudentHeader() {
        System.out.println(LINE_STUDENT);
        System.out.println(HEADER_STUDENT);
        System.out.println(LINE_STUDENT);
    }

    public static void printStudentRow(Student student) {
        System.out.printf("| %-10d | %-23s | %-10s | %-23s | %-6s | %-15s | %-12s |\n",
                student.getId(),
                student.getName(),
                student.getDob() != null ? student.getDob().toString() : "N/A",
                student.getEmail(),
                (student.isSex() ? "Nam" : "Nữ"),
                student.getPhone() != null ? student.getPhone() : "N/A",
                student.getCreateAt() != null ? DateUtils.formatDate(student.getCreateAt()) : "N/A");
        System.out.println(LINE);
    }
}
