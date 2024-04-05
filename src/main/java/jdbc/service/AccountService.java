package jdbc.service;

import jdbc.model.Account;
import jdbc.repository.AccountRepository;

import java.util.List;

public class AccountService {
    private AccountRepository accountRepository = new AccountRepository();
    public void addAccount(Account account) {
        accountRepository.addAccount(account);
    }

    public void addAccount(String username, String password) {
        Account account = new Account(username, password);
        this.addAccount(account);
    }

    public Account getAccount(String username) {
        return accountRepository.getAccount(username);
    }

    public List<Account> getAccounts() {
        return accountRepository.getAllAccounts();
    }

    public void deleteAccount(String username) {
        accountRepository.deleteAccount(username);
    }
}
