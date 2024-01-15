package micro.service.chessservice.entity.exception;

public class WrongMoveException extends Exception {
    public WrongMoveException(String message) {
        super(message);
    }
}
