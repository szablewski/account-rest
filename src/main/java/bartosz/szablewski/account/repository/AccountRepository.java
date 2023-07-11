package bartosz.szablewski.account.repository;

import bartosz.szablewski.account.model.Account;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class AccountRepository {

    private Set<Account> accounts = new HashSet<>();

    public Set<Account> addAccount(Account account) {
        accounts.add(account);
        return accounts;
    }

    public void clearListAccount() {
        accounts = new HashSet<>();
    }

    public Set<Account> getAccounts() {
        return accounts;
    }
}
