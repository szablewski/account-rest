package bartosz.szablewski.account.exception;

import bartosz.szablewski.account.model.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
@Slf4j
public class AccountErrorHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {

        String errorMessage = ex.getBindingResult().getAllErrors()
                .stream()
                .findFirst()
                .orElseThrow()
                .getDefaultMessage();

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(400);
        errorResponse.setMessage(errorMessage);

        log.error("Validation error: {}", errorResponse);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleAccountNotFoundExceptions(AccountNotFoundException ex) {

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(404);
        errorResponse.setMessage(ex.getMessage());

        log.error("Not found error: {}", errorResponse);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
}
