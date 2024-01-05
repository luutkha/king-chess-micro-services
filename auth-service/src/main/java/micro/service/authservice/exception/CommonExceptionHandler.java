package micro.service.authservice.exception;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Log4j2
public class CommonExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value
            = {
//            RuntimeException.class, IllegalArgumentException.class, IllegalStateException.class,
            ExpiredJwtException.class})

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ResponseEntity<Object> handleExpiredToken(
            RuntimeException ex, WebRequest request) {
        log.info("EXPIRED EXCEPTION HANDLED");
        String bodyOfResponse = "Expired TOKEN";
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(value
            = {
//            RuntimeException.class, IllegalArgumentException.class, IllegalStateException.class,
            AuthenticationException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ResponseEntity<Object> handleLoginFail(
            RuntimeException ex, WebRequest request) {
        log.info("LOGIN FAIL");
        String bodyOfResponse = "Username/Password wrong";
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

}
