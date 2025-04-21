package ra.edu.business.service.account;

import ra.edu.business.dao.account.AccountDao;
import ra.edu.business.dao.account.AccountDaoImp;
import ra.edu.business.model.Account;

public class AccountServiceImp implements AccountService {
    private final AccountDao accountDao;

    public AccountServiceImp() {
        accountDao = new AccountDaoImp();
    }

    @Override
    public Account login(String email, String password) {
        return accountDao.login(email, password);
    }
}
