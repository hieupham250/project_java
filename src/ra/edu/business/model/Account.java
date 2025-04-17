package ra.edu.business.model;

import ra.edu.business.IApp;
import ra.edu.datatype.Role;
import ra.edu.datatype.StatusAccount;
import ra.edu.validate.StringRule;
import ra.edu.validate.Validator;

import java.util.Scanner;

public class Account implements IApp {
    private int id;
    private String email;
    private String password;
    private Role role;
    private StatusAccount status;

    public Account() {}

    public Account(int id, String email, String password, Role role, StatusAccount status) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.role = role;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public StatusAccount getStatus() {
        return status;
    }

    public void setStatus(StatusAccount status) {
        this.status = status;
    }

    @Override
    public void inputData(Scanner sc) {
        this.email = Validator.validateInputString("Nhập email: ", sc, new StringRule(100, "Email không được để trống!"));
        this.password = Validator.validateInputString("Nhập mật khẩu: ", sc, new StringRule(100, "Mật khẩu không được để trống!"));
    }
}
