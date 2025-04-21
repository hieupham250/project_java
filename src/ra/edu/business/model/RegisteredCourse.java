package ra.edu.business.model;

import ra.edu.datatype.StatusEnrollment;

import java.sql.Timestamp;

public class RegisteredCourse {
    private int courseId;
    private String courseName;
    private Timestamp registeredAt;
    private StatusEnrollment status;

    public RegisteredCourse() {}

    public RegisteredCourse(int courseId, String courseName, Timestamp registeredAt, StatusEnrollment status) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.registeredAt = registeredAt;
        this.status = status;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
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
