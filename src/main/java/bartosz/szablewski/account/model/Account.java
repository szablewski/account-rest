package bartosz.szablewski.account.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Setter
@Getter
@EqualsAndHashCode
public class Account {

    private Long accountId;
    private String firstName;
    private String lastName;
    private BigDecimal accountBalance;
    private String currency;
}
