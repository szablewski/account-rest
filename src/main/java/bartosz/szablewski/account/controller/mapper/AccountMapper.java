package bartosz.szablewski.account.controller.mapper;

import bartosz.szablewski.account.controller.dto.AccountDto;
import bartosz.szablewski.account.model.Account;

public class AccountMapper {

    public static Account accountMapper(AccountDto accountDto) {
        Account account = new Account();
        account.setAccountId(accountDto.getAccountId());
        account.setFirstName(accountDto.getFirstName());
        account.setLastName(accountDto.getLastName());
        account.setAccountBalance(accountDto.getAccountBalance());
        account.setCurrency(accountDto.getCurrency());

        return account;
    }

    public static AccountDto accountDtoMapper(Account account) {
        AccountDto accountDto = new AccountDto();
        accountDto.setAccountId(account.getAccountId());
        accountDto.setFirstName(account.getFirstName());
        accountDto.setLastName(account.getLastName());
        accountDto.setAccountBalance(account.getAccountBalance());
        accountDto.setCurrency(account.getCurrency());

        return accountDto;
    }
}
