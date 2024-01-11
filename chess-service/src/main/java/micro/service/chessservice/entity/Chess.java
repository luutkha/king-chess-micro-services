package micro.service.chessservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import micro.service.chessservice.constant.ChessUnitConstant;
import micro.service.chessservice.constant.SideConstant;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Chess {
    //    private String name;
//    private ChessUnit type;
    private ChessUnitConstant type;

    private SideConstant side;  // black or white
    private Square position;
    private Set<Square> possibleMoves;

    public Set<Square> generatePossibleMoves(Set<Square> possibleMoves, List<Chess> chessMaps) {
        return this.getPossibleMoves();
    }
//    public Chess(Side side, Square position, Set<Square> possibleMoves) {
//        this.side = side;
//        this.position = position;
//        this.possibleMoves = possibleMoves;
//    }
}
