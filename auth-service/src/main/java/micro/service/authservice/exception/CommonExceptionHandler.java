package micro.service.authservice.exception;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.SignatureException;
import lombok.extern.log4j.Log4j2;
import micro.service.authservice.entity.response.Response;
import micro.service.authservice.entity.response.ValidateTokenResponse;
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
    private static String generateErrorMessage(RuntimeException ex) {
        String errorMessage;
        if (ex instanceof SignatureException) {
            errorMessage = "Signature Exception";
        } else if (ex instanceof ExpiredJwtException) {
            errorMessage = "Expired JWT Exception";
        } else if (ex instanceof JwtException) {
            errorMessage = "JWT Exception";
        } else {
            errorMessage = "Unknown Exception";
        }
        return errorMessage;
    }

    @ExceptionHandler(value
            = {
            SignatureException.class,
            ExpiredJwtException.class,
            SignatureException.class,
            JwtException.class,
    })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ResponseEntity<Response<Object>> handleExpiredToken(
            RuntimeException ex, WebRequest request) {
        log.error("TOKEN EXCEPTION HANDLED");
        String errorMessage;
        errorMessage = generateErrorMessage(ex);
        ValidateTokenResponse bodyOfResponse = ValidateTokenResponse.builder().isValid(false).build();
        Response<Object> validateTokenResponseResponse = Response.builder().data(bodyOfResponse).statusCode(500).message(errorMessage).build();
        return new ResponseEntity<>(validateTokenResponseResponse, HttpStatus.UNAUTHORIZED);

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
