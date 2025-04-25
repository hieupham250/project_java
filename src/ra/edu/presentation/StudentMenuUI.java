package ra.edu.presentation;

import ra.edu.business.model.Account;
import ra.edu.business.service.course.CourseService;
import ra.edu.business.service.course.CourseServiceImp;
import ra.edu.business.service.student.StudentService;
import ra.edu.business.service.student.StudentServiceImp;
import ra.edu.datatype.PaginationOption;
import ra.edu.utils.PaginationUtils;
import ra.edu.validate.StringRule;
import ra.edu.validate.Validator;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.Scanner;

public class StudentMenuUI {
    public static CourseService courseService = new CourseServiceImp();
    public static StudentService studentService = new StudentServiceImp();

    public static void displayMenu(Scanner sc) {
        do {
            System.out.println("========= MENU HỌC VIÊN =========");
            System.out.println(" 1. Xem danh sách khóa học");
            System.out.println(" 2. Tìm kiếm khóa học");
            System.out.println(" 3. Đăng ký khóa học");
            System.out.println(" 4. Xem khóa học đã đăng ký");
            System.out.println(" 5. Hủy đăng ký (nếu chưa bắt đầu)");
            System.out.println(" 6. Đổi mật khẩu");
            System.out.println(" 7. Đăng xuất");
            System.out.println("=================================");
            int choice = Validator.validateInputInteger("Nhập lựa chọn: ", sc);
            switch (choice) {
                case 1:
                    CourseManager.displayCourses(sc);
                    break;
                case 2:
                    CourseManager.searchCourseByName(sc);
                    break;
                case 3:
                    registerCourse(sc);
                    break;
                case 4:
                    displayCourseRegistration(sc);
                    break;
                case 5:
                    cancelCourseRegistration(sc);
                    break;
                case 6:
                    changePassword(sc);
                    break;
                case 7:
                    LoginUI.logout();
                    return;
                default:
                    System.out.println("\u001B[31mLựa chọn không hợp lệ!\u001B[0m");
            }
        } while (true);
    }

    public static void registerCourse(Scanner sc) {
        int courseId = Validator.validateInputInteger("Nhập mã khóa học muốn đăng ký: ", sc);
        if (courseService.getCourseById(courseId) == null) {
            System.out.println("\u001B[31mMã khóa học không tồn tại!\u001B[0m");
            return;
        }
        Account currentAccount = LoginUI.getLoggedInAccount();
        int studentId = currentAccount.getStudentId();
        if (studentService.registerCourse(studentId, courseId)) {
            System.out.println("\u001B[32mĐăng ký khóa học thành công!\u001B[0m");
        } else {
            System.out.println("\u001B[31mBạn đã đăng ký khóa học này hoặc xảy ra lỗi!\u001B[0m");
        }
    }

    public static void displayCourseRegistration(Scanner sc) {
        Account currentAccount = LoginUI.getLoggedInAccount();
        int studentId = currentAccount.getStudentId();
        PaginationUtils<StudentService> pagination = new PaginationUtils<>(studentId, PaginationOption.COURSE_REGISTERED, studentService);
        pagination.paginate(sc);
    }

    public static void cancelCourseRegistration(Scanner sc) {
        int courseId = Validator.validateInputInteger("Nhập mã khóa học muốn hủy đăng ký: ", sc);
        if (courseService.getCourseById(courseId) == null) {
            System.out.println("\u001B[31mMã khóa học không tồn tại!\u001B[0m");
            return;
        }
        Account currentAccount = LoginUI.getLoggedInAccount();
        int studentId = currentAccount.getStudentId();
        if (studentService.cancelCourseRegistration(studentId, courseId)) {
            System.out.println("\u001B[32mHủy đăng ký khóa học thành công!\u001B[0m");
        } else {
            System.out.println("\u001B[31mHủy đăng ký khóa học không thành công hoặc khóa học đã bắt đầu!\u001B[0m");
        }
    }

        public static void changePassword(Scanner sc) {
            Account currentAccount = LoginUI.getLoggedInAccount();
            String currentEmail = currentAccount.getEmail();
            String currentPassword = Validator.validateInputString("Nhập mật khẩu hiện tại: ", sc, new StringRule(255, "Không được để trống!"));
            String newPassword = Validator.validateInputString("Nhập mật khẩu mới: ", sc, new StringRule(255, "Không được để trống!"));
            String confirmPassword = Validator.validateInputString("Xác nhận mật khẩu mới: ", sc, new StringRule(255, "Không được để trống!"));
            if (!currentPassword.equals(currentAccount.getPassword())) {
                System.out.println("\u001B[31mMật khẩu cũ không đúng!\u001B[0m");
                return;
            }
            if (!newPassword.equals(confirmPassword)) {
                System.out.println("\u001B[31mMật khẩu mới không khớp!\u001B[0m");
                return;
            }

            // Gửi email chứa mã xác nhận
            String email = currentAccount.getEmail(); // Lấy địa chỉ email người dùng
            String verificationCode = generateVerificationCode(); // Tạo mã xác nhận ngẫu nhiên

            // Cấu hình thông tin gửi mail
            String SMTP_HOST = "smtp.gmail.com";  // SMTP của Gmail (hoặc thay đổi nếu dùng dịch vụ khác)
            String SMTP_PORT = "587";  // Port của Gmail SMTP
            String FROM_EMAIL  = "tuthan1274@gmail.com";
            String PASSWORD = "qzme cmrm tjua hhpf";

            // Cấu hình các thông tin gửi email
            Properties properties = new Properties();
            properties.put("mail.smtp.host", SMTP_HOST);
            properties.put("mail.smtp.port", SMTP_PORT);
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");

            // Tạo một session với thông tin xác thực
            Session session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(FROM_EMAIL, PASSWORD);
                }
            });

            try {
                // Tạo nội dung email
                String subject = "Mã xác nhận thay đổi mật khẩu";
                String messageText = "Mã xác nhận của bạn là: " + verificationCode;

                // Tạo đối tượng message
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(FROM_EMAIL));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
                message.setSubject(subject);
                message.setText(messageText);

                // Gửi email
                Transport.send(message);
                System.out.println("Email đã được gửi thành công!");

            } catch (MessagingException e) {
                e.printStackTrace();
                System.out.println("Có lỗi khi gửi email!");
                return;
            }


            String enteredCode = Validator.validateInputString("Nhập mã xác nhận đã gửi qua email: ", sc, new StringRule(255, "Không được để trống!"));
            // Kiểm tra mã xác nhận
            if (!enteredCode.equals(verificationCode)) {
                System.out.println("\u001B[31mMã xác nhận không chính xác!\u001B[0m");
                return;
            }

            if (studentService.updatePassword(currentAccount.getStudentId(), newPassword)) {
                System.out.println("\u001B[32mĐổi mật khẩu thành công!\u001B[0m");
            } else {
                System.out.println("\u001B[31mCó lỗi khi cập nhật mật khẩu!\u001B[0m");
            }
        }

    // Phương thức tạo mã xác nhận ngẫu nhiên
    private static String generateVerificationCode() {
        int code = (int) (Math.random() * 1000000);
        return String.format("%06d", code);
    }
}
