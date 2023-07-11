package bartosz.szablewski.account.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class Tables {

    private String code;
    private List<Rates> rates;
}
