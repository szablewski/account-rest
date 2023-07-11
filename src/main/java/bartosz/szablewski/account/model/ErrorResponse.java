package bartosz.szablewski.account.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class ErrorResponse {

    private int code;
    private String message;
}
