package ra.edu.business.service.account;

import ra.edu.business.model.Account;

public interface AccountService {
    Account login(String email, String password);
    void logout();
}
