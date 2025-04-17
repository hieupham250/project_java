package ra.edu.business.model;

import java.time.LocalDate;

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

    public LocalDate getCreate_at() {
        return create_at;
    }

    public void setCreate_at(LocalDate create_at) {
        this.create_at = create_at;
    }

    @Override
    public String toString() {
        return String.format(
                "| %-3s | %-30s | %-8s | %-20s | %-12s |\n" +
                        "| %-3d | %-30s | %-8d | %-20s | %-12s |",
                "ID", "Course Name", "Duration", "Instructor", "Create At",
                id, name, duration, instructor, create_at
        );
    }
}
