package ra.edu.utils;

import ra.edu.business.model.Course;
import ra.edu.business.model.RegisteredCourse;
import ra.edu.business.model.Student;

import java.util.List;

public class TableUtils {
    private static final String LINE_COURSE = "+--------------+------------------------------------------+------------+------------------------+--------------+";
    private static final String HEADER_COURSE = String.format(
            "| %-12s | %-40s | %-10s | %-22s | %-12s |",
            "Mã khóa học", "Tên khóa học", "Thời lượng", "Giảng viên", "Ngày tạo"
    );

    private static final String LINE_STUDENT = "+--------------+-------------------------+------------+-------------------------+-----------+-----------------+-----------------+--------------+";
    private static final String HEADER_STUDENT = String.format(
            "| %-12s | %-23s | %-10s | %-23s | %-9s | %-15s | %-15s | %-12s |",
            "Mã sinh viên", "Tên sinh viên", "Ngày sinh", "Email", "Giới tính", "Số điện thoại", "Trạng thái", "Ngày tạo"
    );

    private static final String LINE_REGISTERED_COURSE = "+--------------+------------------------------------------+---------------------+-------------+";
    private static final String HEADER_REGISTERED_COURSE = String.format(
            "| %-12s | %-40s | %-19s | %-11s |",
            "Mã khóa học","Tên khóa học", "Ngày đăng ký", "Trạng thái"
    );

    public static void printCoursesTable(List<Course> courses) {
        System.out.println(LINE_COURSE);
        System.out.println(HEADER_COURSE);
        System.out.println(LINE_COURSE);
        for (Course course : courses) {
            System.out.printf("| %-12d | %-40s | %-10d | %-22s | %-12s |\n",
                    course.getId(), course.getName(), course.getDuration(), course.getInstructor(), DateUtils.formatDate(course.getCreateAt())
            );
            System.out.println(LINE_COURSE);
        }
    }

    public static void printCourseTable(Course course) {
        System.out.println(LINE_COURSE);
        System.out.println(HEADER_COURSE);
        System.out.println(LINE_COURSE);
        System.out.printf("| %-12d | %-40s | %-10d | %-22s | %-12s |\n",
                course.getId(), course.getName(), course.getDuration(), course.getInstructor(), DateUtils.formatDate(course.getCreateAt())
        );
        System.out.println(LINE_COURSE);
    }

    public static void printStudentsTable(List<Student> students) {
        System.out.println(LINE_STUDENT);
        System.out.println(HEADER_STUDENT);
        System.out.println(LINE_STUDENT);
        for (Student student : students) {
            System.out.printf("| %-12d | %-23s | %-10s | %-23s | %-9s | %-15s | %-15s | %-12s |\n",
                    student.getId(),
                    student.getName(),
                    student.getDob() != null ? DateUtils.formatDate(student.getDob()) : "N/A",
                    student.getEmail(),
                    (student.isSex() ? "Nam" : "Nữ"),
                    student.getPhone() != null ? student.getPhone() : "N/A",
                    student.getStatus() != null ? student.getStatus().name() : "N/A",
                    student.getCreateAt() != null ? DateUtils.formatDate(student.getCreateAt()) : "N/A"
            );
            System.out.println(LINE_STUDENT);
        }
    }

    public static void printStudentTable(Student student) {
        System.out.println(LINE_STUDENT);
        System.out.println(HEADER_STUDENT);
        System.out.println(LINE_STUDENT);
        System.out.printf("| %-12d | %-23s | %-10s | %-23s | %-9s | %-15s | %-15s | %-12s |\n",
                student.getId(),
                student.getName(),
                student.getDob() != null ? DateUtils.formatDate(student.getDob()) : "N/A",
                student.getEmail(),
                (student.isSex() ? "Nam" : "Nữ"),
                student.getPhone() != null ? student.getPhone() : "N/A",
                student.getStatus() != null ? student.getStatus().name() : "N/A",
                student.getCreateAt() != null ? DateUtils.formatDate(student.getCreateAt()) : "N/A"
        );
        System.out.println(LINE_STUDENT);
    }

    public static void printRegisteredCoursesTable(List<RegisteredCourse> registeredCourses) {
        System.out.println(LINE_REGISTERED_COURSE);
        System.out.println(HEADER_REGISTERED_COURSE);
        System.out.println(LINE_REGISTERED_COURSE);
        for (RegisteredCourse rc : registeredCourses) {
            String statusDisplay = rc.getStatus() != null ? rc.getStatus().toVietnamese() : "N/A";
            System.out.printf("| %-12d | %-40s | %-19s | %-11s |\n",
                    rc.getCourseId(),
                    rc.getCourseName(),
                    rc.getRegisteredAt() != null ? DateUtils.formatDateTime(rc.getRegisteredAt()) : "N/A",
                    statusDisplay
            );
            System.out.println(LINE_REGISTERED_COURSE);
        }
    }
}
