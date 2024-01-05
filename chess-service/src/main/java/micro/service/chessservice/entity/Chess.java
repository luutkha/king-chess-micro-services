package micro.service.chessservice.entity;

import lombok.Data;
import lombok.Getter;
import micro.service.chessservice.constant.ChessUnit;
import micro.service.chessservice.constant.Side;

@Data
public class Chess {
    private String name;
    private ChessUnit type; // black or white
    private Side side;
    private Square position;
}
