package ra.edu.business.model;

import ra.edu.datatype.StatusEnrollment;

import java.sql.Timestamp;

public class RegisteredStudentInfo {
    private int studentId;
    private String nameStudent;
    private String email;
    private String phone;
    private int courseId;
    private String nameCourse;
    private Timestamp registeredAt;
    private StatusEnrollment status;

    public RegisteredStudentInfo() {}

    public RegisteredStudentInfo(int studentId, String nameStudent, String email, String phone, int courseId,
                                 String nameCourse, Timestamp registeredAt, StatusEnrollment status) {
        this.studentId = studentId;
        this.nameStudent = nameStudent;
        this.email = email;
        this.phone = phone;
        this.courseId = courseId;
        this.nameCourse = nameCourse;
        this.registeredAt = registeredAt;
        this.status = status;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getNameStudent() {
        return nameStudent;
    }

    public void setNameStudent(String nameStudent) {
        this.nameStudent = nameStudent;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getNameCourse() {
        return nameCourse;
    }

    public void setNameCourse(String nameCourse) {
        this.nameCourse = nameCourse;
    }

    public Timestamp getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(Timestamp registeredAt) {
        this.registeredAt = registeredAt;
    }

    public StatusEnrollment getStatus() {
        return status;
    }

    public void setStatus(StatusEnrollment status) {
        this.status = status;
    }
}
