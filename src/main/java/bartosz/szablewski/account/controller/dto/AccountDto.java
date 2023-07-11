package bartosz.szablewski.account.controller.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
public class AccountDto {

    private Long accountId;
    @NotEmpty(message = "firstName cannot be empty")
    @NotNull(message = "firstName cannot be null")
    private String firstName;
    @NotEmpty(message = "lastName cannot be empty")
    @NotNull(message = "lastname cannot be null")
    private String lastName;
    @DecimalMin(value = "0.00", message = "account balance cannot be negative")
    @NotNull(message = "account balance cannot be null")
    @Digits(integer=3, fraction=2)
    private BigDecimal accountBalance;
    private String currency;
}
