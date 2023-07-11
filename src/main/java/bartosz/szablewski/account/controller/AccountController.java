package bartosz.szablewski.account.controller;

import bartosz.szablewski.account.controller.dto.AccountDto;
import bartosz.szablewski.account.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity createAccount(@Valid @RequestBody AccountDto accountDto) {
        accountService.createAccount(accountDto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<AccountDto> getAccount(@PathVariable long accountId) {
        AccountDto accountDto = accountService.getAccountById(accountId);

        return new ResponseEntity<>(accountDto, HttpStatus.OK);
    }

    @PostMapping("/{accountId}/exchange")
    public ResponseEntity<AccountDto> exchangeAccountBalance(@PathVariable long accountId, @RequestParam String code) {
        AccountDto accountDto = accountService.exchangeBalanceCurrency(code, accountId);

        return new ResponseEntity<>(accountDto, HttpStatus.OK);
    }

}
