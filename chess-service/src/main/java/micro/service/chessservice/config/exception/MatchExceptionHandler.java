package micro.service.chessservice.config.exception;

import lombok.extern.log4j.Log4j2;
import micro.service.chessservice.entity.exception.WrongMoveException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Log4j2
public class MatchExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { WrongMoveException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ResponseEntity<Object> handleLoginFail(
            RuntimeException ex, WebRequest request) {
        log.error("CATCH WrongMoveException ERROR");
        String bodyOfResponse = ex.getMessage().toUpperCase();
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }
}
