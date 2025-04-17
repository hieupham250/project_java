package ra.edu.business.model;

import ra.edu.validate.CourseValidator;
import ra.edu.validate.StringRule;
import ra.edu.validate.Validator;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import static ra.edu.utils.DateUtils.formatDate;

public class Course {
    private int id;
    private String name;
    private int duration;
    private String instructor;
    private LocalDate create_at;

    public Course() {}

    public Course(int id, String name, int duration, String instructor, LocalDate create_at) {
        this.id = id;
        this.name = name;
        this.duration = duration;
        this.instructor = instructor;
        this.create_at = create_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public LocalDate getCreateAt() {
        return create_at;
    }

    public void setCreateAt(LocalDate create_at) {
        this.create_at = create_at;
    }

    public void inputData(Scanner sc, List<Course> existingCourses) {
        while (true) {
            String inputName = Validator.validateInputString("Nhập tên khóa học: ", sc, new StringRule(100, "Tên khóa học không được để trống!"));
            if (CourseValidator.isNameExisted(inputName, existingCourses)) {
                System.out.println("\u001B[31mTên khóa học đã tồn tại. Vui lòng nhập tên khác!\u001B[0m");
            } else {
                this.name = inputName;
                break;
            }
        }
        this.duration = Validator.validateInputInteger("Nhập thời lượng (giờ): ", sc);
        this.instructor = Validator.validateInputString("Nhập giảng viên phụ trách: ", sc, new StringRule(100, "Tên giảng viên không được để trống!"));
    }
}
