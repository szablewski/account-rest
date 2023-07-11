package bartosz.szablewski.account.service;

import bartosz.szablewski.account.model.Account;
import bartosz.szablewski.account.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;

@SpringBootTest
public class AccountServiceTest {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void shouldCreateAccount(){
        //given
        Account account = createAccount();

        //when
        Set<Account> accounts = accountRepository.addAccount(account);

        //then
        assertThat(accounts, hasSize(1));
        assertThat(accounts, hasItem(createAccount()));
    }

    private Account createAccount(){
        Account account = new Account();
        account.setAccountId(1L);
        account.setFirstName("Test");
        account.setLastName("Test");
        account.setAccountBalance(new BigDecimal(100));
        account.setCurrency("PLN");

        return account;
    }
}
