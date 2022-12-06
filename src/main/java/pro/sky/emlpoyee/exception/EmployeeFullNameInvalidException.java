package pro.sky.emlpoyee.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Введены не допустимые символы")
public class EmployeeFullNameInvalidException extends RuntimeException {
    public EmployeeFullNameInvalidException() {
    }

    public EmployeeFullNameInvalidException(String message) {
        super(message);
    }

    public EmployeeFullNameInvalidException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmployeeFullNameInvalidException(Throwable cause) {
        super(cause);
    }

    public EmployeeFullNameInvalidException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
