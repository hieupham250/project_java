package ra.edu.business.model;

import ra.edu.utils.DateUtils;

import java.time.LocalDate;

public class Student {
    private int id;
    private String name;
    private LocalDate dob;
    private String email;
    private boolean sex;
    private String phone;
    private String password;
    private LocalDate create_at;

    public Student() {}

    public Student(int id, String name, LocalDate dob, String email, boolean sex, String phone, String password, LocalDate create_at) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.email = email;
        this.sex = sex;
        this.phone = phone;
        this.password = password;
        this.create_at = create_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getCreateAt() {
        return create_at;
    }

    public void setCreateAt(LocalDate create_at) {
        this.create_at = create_at;
    }

    @Override
    public String toString() {
        return String.format(
                "| %-10s | %-30s | %-10s | %-40s | %-5s | %-15s | %-30s | %-10s |\n" +
                        "| %-10d | %-30s | %-10s | %-40s | %-5s | %-15s | %-30s | %-10s |",
                "ID", "Name", "DOB", "Email", "Sex", "Phone", "Password", "Create At",
                id, name, DateUtils.formatDate(dob), email, (sex ? "Nam" : "Nữ"), phone, password, DateUtils.formatDate(create_at)
        );
    }
}
