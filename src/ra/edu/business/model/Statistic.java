package ra.edu.business.model;

public class Statistic {
    private int courseId;
    private String courseName;
    private int totalCourses;
    private int totalStudents;

    public Statistic() {}

    public Statistic(int totalCourses, int totalStudents) {
        this.totalCourses = totalCourses;
        this.totalStudents = totalStudents;
    }

    public Statistic(int courseId, String courseName, int totalStudents) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.totalStudents = totalStudents;
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

    public int getTotalCourses() {
        return totalCourses;
    }

    public void setTotalCourses(int totalCourses) {
        this.totalCourses = totalCourses;
    }

    public int getTotalStudents() {
        return totalStudents;
    }

    public void setTotalStudents(int totalStudents) {
        this.totalStudents = totalStudents;
    }
}
