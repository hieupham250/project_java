package ra.edu.presentation;

import ra.edu.business.model.Account;
import ra.edu.business.service.account.AccountService;
import ra.edu.business.service.account.AccountServiceImp;

import java.util.Scanner;

public class LoginUI {
    private static final AccountService accountService = new AccountServiceImp();
    private static Account loggedInAccount;

    public static Account login(Scanner sc) {
        System.out.println("========= ĐĂNG NHẬP =======");
        Account account = new Account();
        account.inputData(sc);
        loggedInAccount = accountService.login(account.getEmail(), account.getPassword());
        return loggedInAccount;
    }

    public static void logout() {
        loggedInAccount = null;
        System.out.println("\u001B[32mĐăng xuất thành công!\u001B[0m");
    }

    public static Account getLoggedInAccount() {
        return loggedInAccount;
    }
}
