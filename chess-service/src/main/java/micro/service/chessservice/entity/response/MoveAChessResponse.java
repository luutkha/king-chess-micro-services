package micro.service.chessservice.entity.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import micro.service.chessservice.entity.ChessBoard;
import micro.service.chessservice.entity.MatchHistory;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MoveAChessResponse {
    private ChessBoard chessBoard;
    private MatchHistory matchHistory;
}
