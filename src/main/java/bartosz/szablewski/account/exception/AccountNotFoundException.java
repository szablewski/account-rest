package bartosz.szablewski.account.exception;

import lombok.Getter;

@Getter
public class AccountNotFoundException extends RuntimeException{

    private Long accountId;

    public AccountNotFoundException(Long accountId){
        super(String.format("Account not found with given id: %s", accountId));
        this.accountId = accountId;
    }
}
