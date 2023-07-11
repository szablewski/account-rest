package bartosz.szablewski.account.service;

import bartosz.szablewski.account.controller.dto.AccountDto;
import bartosz.szablewski.account.controller.mapper.AccountMapper;
import bartosz.szablewski.account.exception.AccountNotFoundException;
import bartosz.szablewski.account.model.Account;
import bartosz.szablewski.account.model.Rates;
import bartosz.szablewski.account.model.Tables;
import bartosz.szablewski.account.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Random;

@Service
@Slf4j
public class AccountService {

    private final AccountRepository accountRepository;
    private final RateService rateService;

    @Autowired
    public AccountService(AccountRepository accountRepository, RateService rateService) {
        this.accountRepository = accountRepository;
        this.rateService = rateService;
    }

    public AccountDto getAccountById(Long accountId) {
        Account account = accountRepository.getAccounts().stream()
                .filter(x -> accountId != null)
                .filter(a -> a.getAccountId().equals(accountId))
                .findFirst()
                .orElseThrow(
                        () -> new AccountNotFoundException(accountId));

        return AccountMapper.accountDtoMapper(account);
    }

    public void createAccount(AccountDto accountDto) {
        Account account = AccountMapper.accountMapper(accountDto);
        account.setCurrency("PLN");
        account.setAccountId(generateId());

        accountRepository.addAccount(account);
        log.info("Created account with accountId: {}", account.getAccountId());
    }

    public AccountDto exchangeBalanceCurrency(String code, long accountId) {
        Tables tables = rateService.getRateByCode(code);
        Rates rates = tables.getRates().stream().findFirst().get();
        Account account = (AccountMapper.accountMapper(getAccountById(accountId)));
        BigDecimal newBalance;

        log.info("Exchange account balance with id: {} from: {} to: {}", accountId, account.getCurrency(), tables.getCode());

        accountRepository.clearListAccount();
        if (!account.getCurrency().equals("PLN")) {
            newBalance = calculateBalanceToPLN(account.getAccountBalance(), rates.getAsk());

            account.setAccountBalance(newBalance);
            account.setCurrency("PLN");
        } else {
            newBalance = calculateBalanceFromPLN(account.getAccountBalance(), rates.getBid());

            account.setAccountBalance(newBalance);
            account.setCurrency(tables.getCode());
        }

        accountRepository.addAccount(account);
        return AccountMapper.accountDtoMapper(account);
    }

    private BigDecimal calculateBalanceToPLN(BigDecimal from, BigDecimal to) {
        return from.multiply(to, new MathContext(2));
    }

    private BigDecimal calculateBalanceFromPLN(BigDecimal from, BigDecimal to) {
        return from.divide(to, 2, RoundingMode.CEILING);
    }

    private Long generateId() {
        Random random = new Random();
        long id;

        do {
            id = random.nextLong();
        } while (id <= 0);

        return id;
    }
}
