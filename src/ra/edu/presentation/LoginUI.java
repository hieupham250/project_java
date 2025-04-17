package ra.edu.presentation;

import ra.edu.business.model.Account;
import ra.edu.business.service.account.AccountService;
import ra.edu.business.service.account.AccountServiceImp;

import java.util.Scanner;

public class LoginUI {
    private static final AccountService accountService = new AccountServiceImp();

    public static Account login(Scanner sc) {
        System.out.println("========= ĐĂNG NHẬP =======");
        Account account = new Account();
        account.inputData(sc);
        Account loggedInAccount = accountService.login(account.getEmail(), account.getPassword());
        if (loggedInAccount != null) {
            return loggedInAccount;
        } else {
            return null;
        }
    }

    public static void logout() {
        accountService.logout();
        System.out.println("\u001B[32mĐăng xuất thành công!\u001B[0m");
    }
}
