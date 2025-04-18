package ra.edu.business.model;

import ra.edu.business.service.student.StudentService;
import ra.edu.utils.DateUtils;
import ra.edu.validate.StringRule;
import ra.edu.validate.StudentValidator;
import ra.edu.validate.Validator;

import java.time.LocalDate;
import java.util.Scanner;

public class Student {
    private int id;
    private String name;
    private LocalDate dob;
    private String email;
    private String password;
    private boolean sex;
    private String phone;
    private int account_id;
    private LocalDate create_at;

    public Student() {}

    public Student(int id, String name, LocalDate dob, String email, String password, boolean sex, String phone, int account_id, LocalDate create_at) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.email = email;
        this.password = password;
        this.sex = sex;
        this.phone = phone;
        this.account_id = account_id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public int getAccountId() {
        return account_id;
    }

    public void setAccountId(int account_id) {
        this.account_id = account_id;
    }

    public LocalDate getCreateAt() {
        return create_at;
    }

    public void setCreateAt(LocalDate create_at) {
        this.create_at = create_at;
    }

    public void inputData(Scanner sc, StudentService studentService) {
        this.name = Validator.validateInputString("Nhập tên học viên: ", sc, new StringRule(100, "Tên không được để trống!"));
        this.dob = Validator.validateDate("Nhập ngày sinh: ", sc);
        this.sex = StudentValidator.validateSex("Nhập giới tính (Nam/Nữ): ", sc);
        while (true) {
            String phone = Validator.validateInputString("Nhập số điện thoại: ", sc, new StringRule(15, "Số điện thoại không được để trống!"));
            if (Validator.isValidatePhone(phone)) {
                this.phone = phone;
                break;
            } else {
                System.out.println("\u001B[31mSố điện thoại không hợp lệ. Vui lòng nhập lại!\u001B[0m");
            }
        }
        while (true) {
            String email = Validator.validateInputString("Nhập email: ", sc, new StringRule(100, "Email không được để trống!"));
            if (Validator.isValidateEmail(email)) {
                if (StudentValidator.isEmailExisted(email, studentService)) {
                    System.out.println("\u001B[31mEmail đã tồn tại. Vui lòng nhập email khác!\u001B[0m");
                } else {
                    this.email = email;
                    break;
                }
            } else {
                System.out.println("\u001B[31mEmail không hợp lệ. Vui lòng nhập lại!\u001B[0m");
            }
        }
        this.password = Validator.validateInputString("Nhập mật khẩu: ", sc, new StringRule(255, "Mật khẩu không được để trống!"));
    }
}
