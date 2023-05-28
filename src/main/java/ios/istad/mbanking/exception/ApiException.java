package ios.istad.mbanking.exception;
import ios.istad.mbanking.BaseRest.BaseError;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import javax.security.sasl.AuthenticationException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ApiException {
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(NoSuchFieldException.class)
    public BaseError<?> handleNoSuchFieldException(ResponseStatusException e){
        return BaseError.builder()
                .status(false)
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .timestamp(LocalDateTime.now())
                .message("Something went wrong ..!please check")
                .error(e.getReason())
                .build();
    }
    // Error 404 =(Error Service 0r Sever)
    // Capture all errors
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ResponseStatusException.class)
    public BaseError<?> handleServiceException(ResponseStatusException e){
        return BaseError.builder()
                .status(false)
                .code(e.getStatusCode().value())
                .timestamp(LocalDateTime.now())
                .message("Something went wrong ..!please check")
                .error(e.getReason())
                .build();
    }
    // Error 400
    // Error User Not Enter following rule
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseError<?> handleValidationException(MethodArgumentNotValidException e){
        List<Map<String ,String>>errors=new ArrayList<>();
        for(FieldError error : e.getFieldErrors()){
            Map<String ,String>errorDetails=new HashMap<>();
            errorDetails.put("name",error.getField());
            errorDetails.put("message",error.getDefaultMessage());
            errors.add(errorDetails);
        }
        return BaseError.builder()
                .status(false)
                .code(HttpStatus.BAD_REQUEST.value())
                .timestamp(LocalDateTime.now())
                .message("validation is errors...!")
                .error(errors)
                .build();
    }

}
