package micro.service.chessservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import micro.service.chessservice.constant.ChessUnitConstant;

import java.util.Set;

@Data
@AllArgsConstructor
@Builder
public class ChessUnitMovable {
    private ChessUnitConstant chessUnitConstant;
    private Square currentPosition;
    private Set<Square> movablePositions;

}
