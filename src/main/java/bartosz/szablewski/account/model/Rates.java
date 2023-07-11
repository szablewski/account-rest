package bartosz.szablewski.account.model;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Rates {


    @Digits(integer=3, fraction=2)
    private BigDecimal bid;
    @Digits(integer=3, fraction=2)
    private BigDecimal ask;
}
